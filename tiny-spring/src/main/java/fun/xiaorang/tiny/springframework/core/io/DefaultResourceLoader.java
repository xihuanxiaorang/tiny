package fun.xiaorang.tiny.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 11:44
 */
public class DefaultResourceLoader implements ResourceLoader {
  @Override
  public Resource getResource(final String location) {
    Assert.notNull(location, "Location must not be null");
    if (location.startsWith(CLASSPATH_URL_PREFIX)) {
      return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
    } else {
      try {
        final URL url = new URL(location);
        return new UrlResource(url);
      } catch (MalformedURLException e) {
        return new FileSystemResource(location);
      }
    }
  }
}
