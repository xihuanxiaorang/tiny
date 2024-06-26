package fun.xiaorang.tiny.springframework.context.support;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 23:38
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
  public ClassPathXmlApplicationContext(String configLocation) {
    this(new String[]{configLocation});
  }

  public ClassPathXmlApplicationContext(String[] configLocations) {
    setConfigLocations(configLocations);
    refresh();
  }
}
