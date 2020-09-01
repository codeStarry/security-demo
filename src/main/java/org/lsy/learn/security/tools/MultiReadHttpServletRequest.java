package org.lsy.learn.security.tools;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 解决request只能读取一次body的问题
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    @Getter
    private String _body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = request.getReader();
        String line = "";

        while (null != (line = reader.readLine())) {

            sb.append(line);
        }

        _body = sb.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(_body.getBytes());

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return stream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getJson(ServletRequest request) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = request.getReader();
        String line = StringUtils.EMPTY;

        while (null != (line = reader.readLine())) {
            sb.append(line);
        }

        return sb.toString();
    }
}
