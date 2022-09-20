package top.xiaorang.springframework.test.bean;

import top.xiaorang.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 6:37
 */
public class ProxyFactoryBean implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        return (IUserDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IUserDao.class}, (proxy, method, args) -> {
            if ("toString".equals(method.getName())) return this.toString();
            Map<String, String> userMap = new HashMap<>();
            userMap.put("1001", "小让");
            userMap.put("1002", "小星");
            userMap.put("1003", "小白");
            return "你被代理了 " + method.getName() + "：" + userMap.get(args[0].toString());
        });
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
