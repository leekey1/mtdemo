package cn.com.hyun.framework.http;

import java.util.Objects;

/**
 * Created by hyunwoo
 */
public class HttpAuth {
    private String username;
    private String password;

    public HttpAuth() {
    }

    public HttpAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpAuth)) return false;
        HttpAuth httpAuth = (HttpAuth) o;
        return Objects.equals(getUsername(), httpAuth.getUsername()) &&
                Objects.equals(getPassword(), httpAuth.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpAuth{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
