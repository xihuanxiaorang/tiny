package fun.xiaorang.tiny.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 23:55
 */
public interface Resource {
  InputStream getInputStream() throws IOException;

}
