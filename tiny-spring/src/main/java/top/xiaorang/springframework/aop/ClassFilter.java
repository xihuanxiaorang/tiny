package top.xiaorang.springframework.aop;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:00
 */
public interface ClassFilter {
    /**
     * 切入点是否应该应用于给定的接口或目标类
     *
     * @param clazz 候选目标类
     * @return 建议是否应适用于给定的目标类
     */
    boolean matches(Class<?> clazz);
}
