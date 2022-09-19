package top.xiaorang.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liulei
 * @description 资源接口
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 16:14
 */
public interface Resource {
    /**
     * 获取IO输入流
     *
     * @return IO输入流
     * @throws IOException IO异常信息
     */
    InputStream getInputStream() throws IOException;
}
