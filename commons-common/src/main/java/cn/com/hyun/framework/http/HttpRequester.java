package cn.com.hyun.framework.http;



import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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

public class HttpRequester {

    private final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    private int timeout = 10000;
    private Map<String, String> headers = new HashedMap<>();
    private Map<String, String> parameters = new HashedMap<>();

    public static HttpRequester newInstance() {
        return new HttpRequester();
    }

    public HttpRequester setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HttpRequester setHeader(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public HttpRequester setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public HttpResponse get(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException();
        }

        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet);
    }

    public HttpResponse post(String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException();
        }

        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity = convertRequsetEntity(parameters);
        httpPost.setEntity(entity);

        return executeRequest(httpPost);
    }

    public HttpResponse put(String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException();
        }

        HttpPut httpPut = new HttpPut(url);
        HttpEntity entity = convertRequsetEntity(parameters);
        httpPut.setEntity(entity);

        return executeRequest(httpPut);
    }

    public HttpResponse delete(String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException();
        }

        HttpDelete httpDelete = new HttpDelete(url);
        return executeRequest(httpDelete);
    }

    private HttpEntity convertRequsetEntity(Map<String, String> parameters) {
        String parameterString = "";

        if (null != parameters && parameters.isEmpty()) {
            StringBuilder paramBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                paramBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            parameterString = paramBuilder.substring(0, paramBuilder.length() - 1);
        }

        StringEntity stringEntity = new StringEntity(parameterString, CHARSET_UTF_8);
        stringEntity.setContentType(ContentType.APPLICATION_JSON.toString());
        stringEntity.setContentEncoding(CHARSET_UTF_8.toString());
        return stringEntity;
    }

    private HttpResponse executeRequest(HttpRequestBase httpRequest) {
        RequestConfig config = constructHttpConfig();
        SSLConnectionSocketFactory sslConnectionSocketFactory = constructSSLFactory();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpRequest.setHeader(entry.getKey(), entry.getValue());
        }

        try (CloseableHttpClient httpClient = HttpClients.custom()
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
        return builder.build();
    }
}
