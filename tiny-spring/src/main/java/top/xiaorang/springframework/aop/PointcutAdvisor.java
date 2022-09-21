package top.xiaorang.springframework.aop;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:15
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
