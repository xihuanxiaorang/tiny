package top.xiaorang.springframework.context.support;

import cn.hutool.core.lang.Assert;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 23:51
 */
public abstract class AbstractRefreshableConfigApplicationContext extends AbstractRefreshableApplicationContext {
    private String[] configLocations;

    public void setConfigLocation(String location) {
        setConfigLocations(location);
    }

    protected String[] getConfigLocations() {
        return (this.configLocations != null ? this.configLocations : getDefaultConfigLocations());
    }

    public void setConfigLocations(String... locations) {
        if (locations != null) {
            Assert.noNullElements(locations, "Config locations must not be null");
            this.configLocations = new String[locations.length];
            for (int i = 0; i < locations.length; i++) {
                this.configLocations[i] = locations[i].trim();
            }
        } else {
            this.configLocations = null;
        }
    }

    protected String[] getDefaultConfigLocations() {
        return null;
    }
}
