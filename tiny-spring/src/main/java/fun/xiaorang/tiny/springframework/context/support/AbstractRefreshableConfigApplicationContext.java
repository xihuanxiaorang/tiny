package fun.xiaorang.tiny.springframework.context.support;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 23:31
 */
public abstract class AbstractRefreshableConfigApplicationContext extends AbstractRefreshableApplicationContext {
  private String[] configLocations;

  public String[] getConfigLocations() {
    return configLocations;
  }

  public void setConfigLocations(final String... locations) {
    this.configLocations = locations;
  }

  public void setConfigLocation(final String location) {
    this.setConfigLocations(location);
  }
}
