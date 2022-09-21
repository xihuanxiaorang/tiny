package top.xiaorang.springframework.aop;

import top.xiaorang.springframework.aop.framework.AdvisedSupport;
import top.xiaorang.springframework.aop.framework.AopProxy;
import top.xiaorang.springframework.aop.framework.AopProxyFactory;
import top.xiaorang.springframework.aop.framework.CglibAopProxy;
import top.xiaorang.springframework.aop.framework.JdkDynamicAopProxy;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:58
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
        if (config.isProxyTargetClass()) {
            return new CglibAopProxy(config);
        }
        return new JdkDynamicAopProxy(config);
    }
}
