package top.xiaorang.springframework.aop.framework;

import cn.hutool.core.lang.Assert;
import top.xiaorang.springframework.aop.DefaultAopProxyFactory;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 16:54
 */
public class ProxyFactory extends AdvisedSupport {
    private AopProxyFactory aopProxyFactory;

    public ProxyFactory() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    public ProxyFactory(AopProxyFactory aopProxyFactory) {
        Assert.notNull(aopProxyFactory, "AopProxyFactory must not be null");
        this.aopProxyFactory = aopProxyFactory;
    }

    /**
     * Return the AopProxyFactory that this ProxyConfig uses.
     */
    public AopProxyFactory getAopProxyFactory() {
        return this.aopProxyFactory;
    }

    /**
     * Customize the AopProxyFactory, allowing different strategies
     * to be dropped in without changing the core framework.
     * <p>Default is {@link DefaultAopProxyFactory}, using dynamic JDK
     * proxies or CGLIB proxies based on the requirements.
     */
    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        Assert.notNull(aopProxyFactory, "AopProxyFactory must not be null");
        this.aopProxyFactory = aopProxyFactory;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(this);
    }
}
