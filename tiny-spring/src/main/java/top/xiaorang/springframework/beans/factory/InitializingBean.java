package top.xiaorang.springframework.beans.factory;

/**
 * @author liulei
 * @description 实现此接口的bean对象，会在bean实例设置属性后作出相应的处理，如：执行自定义初始化，或者仅仅检查是否设置了所有强制属性。
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 1:46
 */
public interface InitializingBean {
    /**
     * bean实例属性填充后调用该方法
     *
     * @throws Exception 异常信息
     */
    void afterPropertiesSet() throws Exception;
}
