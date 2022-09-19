package top.xiaorang.springframework.core.io;

/**
 * @author liulei
 * @description 资源加载器接口
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 16:31
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据资源路径获取资源
     *
     * @param location 资源路径
     * @return 资源
     */
    Resource getResource(String location);
}
