package top.xiaorang.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liulei
 * @description 网络资源
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 16:27
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    public UrlResource(String path) throws MalformedURLException {
        Assert.notNull(path, "Path must not be null");
        this.url = new URL(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            // Close the HTTP connection (if applicable).
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}
