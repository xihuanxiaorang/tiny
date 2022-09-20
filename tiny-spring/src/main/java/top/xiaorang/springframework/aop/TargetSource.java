package top.xiaorang.springframework.aop;

/**
 * @author liulei
 * @description 被代理的目标对象
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 2:58
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetInterfaces() {
        return this.target.getClass().getInterfaces();
    }

    public Class<?> getTargetClass() {
        return this.target.getClass();
    }

    public Object getTarget() throws Exception {
        return this.target;
    }
}
