package fun.xiaorang.tiny.springframework.core.io;

import cn.hutool.core.lang.Assert;
import fun.xiaorang.tiny.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 23:56
 */
public class ClassPathResource implements Resource {
  private final String path;
  private final ClassLoader classLoader;

  public ClassPathResource(final String path) {
    this(path, null);
  }

  public ClassPathResource(final String path, final ClassLoader classLoader) {
    Assert.notNull(path, "Path must not be null");
    this.path = path;
    this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
  }

  @Override
  public InputStream getInputStream() throws IOException {
    InputStream is = this.classLoader.getResourceAsStream(this.path);
    if (is == null) {
      throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
    }
    return is;
  }
}
