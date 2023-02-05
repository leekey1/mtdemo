package cn.com.hyun.framework.http;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by hyunwoo
 */
public class HttpResponse {

    private int status;
    private String content;
    private Header[] headers;
    private ProtocolVersion protocolVersion;

    public static HttpResponse empty() {
        return new HttpResponse();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpResponse)) return false;
        HttpResponse that = (HttpResponse) o;
        return getStatus() == that.getStatus() &&
                Objects.equals(getContent(), that.getContent()) &&
                Arrays.equals(getHeaders(), that.getHeaders()) &&
                Objects.equals(getProtocolVersion(), that.getProtocolVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getContent(), getHeaders(), getProtocolVersion());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpResponse{");
        sb.append("status=").append(status);
        sb.append(", content='").append(content).append('\'');
        sb.append(", headers=").append(Arrays.toString(headers));
        sb.append(", protocolVersion=").append(protocolVersion);
        sb.append('}');
        return sb.toString();
    }
}
