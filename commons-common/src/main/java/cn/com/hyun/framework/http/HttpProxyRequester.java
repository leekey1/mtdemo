package cn.com.hyun.framework.http;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpProxyRequester {

    private final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    private int timeout = 10000;
    private Map<String, String> headers = new HashedMap<>();
    private Map<String, String> parameters = new HashedMap<>();
    private HttpProxy httpProxy;
    private HttpAuth httpAuth;

    public HttpProxyRequester(HttpProxy httpProxy) {
        if (null == httpProxy) {
            throw new NullPointerException();
        }
        this.httpProxy = httpProxy;
    }

    public HttpProxyRequester setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HttpProxyRequester setHeader(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public HttpProxyRequester setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public HttpProxyRequester setProxy(HttpProxy httpProxy) {
        this.httpProxy = httpProxy;
        return this;
    }

    public HttpProxyRequester setAuth(HttpAuth httpAuth) {
        this.httpAuth = httpAuth;
        return this;
    }


    public HttpResponse get(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException();
        }

        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet);
    }

    private HttpResponse executeRequest(HttpRequestBase httpRequest) {
        RequestConfig config = constructHttpConfig();
        SSLConnectionSocketFactory sslConnectionSocketFactory = constructSSLFactory();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpRequest.setHeader(entry.getKey(), entry.getValue());
        }

        CredentialsProvider credentialsProvider = null;
        if (null != httpAuth) {
            credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    new AuthScope(httpProxy.getHost(), httpProxy.getPort()),
                    new UsernamePasswordCredentials(httpAuth.getUsername(), httpAuth.getPassword()));
        }

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        HttpHost target = new HttpHost(httpProxy.getHost(), httpProxy.getPort(), "http");
        authCache.put(target, basicAuth);

        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider)
                .setSSLSocketFactory(sslConnectionSocketFactory).setDefaultRequestConfig(config).build();
             CloseableHttpResponse response = httpClient.execute(httpRequest)) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatus(response.getStatusLine().getStatusCode());
            httpResponse.setContent(EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8")));
            httpResponse.setHeaders(response.getAllHeaders());
            httpResponse.setProtocolVersion(response.getProtocolVersion());
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return HttpResponse.empty();
    }

    private SSLConnectionSocketFactory constructSSLFactory() {
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
                //do nothing
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
                //do nothing
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        return new SSLConnectionSocketFactory(sslContext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    private RequestConfig constructHttpConfig() {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setRedirectsEnabled(true);
        builder.setCircularRedirectsAllowed(true);
        builder.setConnectTimeout(timeout);
        builder.setConnectionRequestTimeout(timeout);
        builder.setSocketTimeout(timeout);
        builder.setProxy(new HttpHost(httpProxy.getHost(), httpProxy.getPort()));
        return builder.build();
    }
}
