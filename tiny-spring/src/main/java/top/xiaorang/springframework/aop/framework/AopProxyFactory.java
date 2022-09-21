package top.xiaorang.springframework.aop.framework;

import top.xiaorang.springframework.aop.AopConfigException;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:54
 */
public interface AopProxyFactory {
    /**
     * Create an {@link AopProxy} for the given AOP configuration.
     *
     * @param config the AOP configuration in the form of an
     *               AdvisedSupport object
     * @return the corresponding AOP proxy
     * @throws AopConfigException if the configuration is invalid
     */
    AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException;
}
