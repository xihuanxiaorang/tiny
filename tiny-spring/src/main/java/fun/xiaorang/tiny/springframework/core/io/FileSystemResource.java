package fun.xiaorang.tiny.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 11:37
 */
public class FileSystemResource implements Resource {
  private final File file;
  private final String path;

  public FileSystemResource(final File file) {
    this.file = file;
    this.path = file.getPath();
  }

  public FileSystemResource(final String path) {
    this.path = path;
    this.file = new File(path);
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new FileInputStream(this.file);
  }
}
