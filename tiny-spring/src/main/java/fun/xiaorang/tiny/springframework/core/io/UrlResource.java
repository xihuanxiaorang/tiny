package fun.xiaorang.tiny.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 11:40
 */
public class UrlResource implements Resource {
  private final URL url;

  public UrlResource(URL url) {
    Assert.notNull(url, "URL must not be null");
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    final URLConnection connection = this.url.openConnection();
    try {
      return connection.getInputStream();
    } catch (IOException e) {
      if (connection instanceof HttpURLConnection) {
        ((HttpURLConnection) connection).disconnect();
      }
      throw e;
    }
  }
}
