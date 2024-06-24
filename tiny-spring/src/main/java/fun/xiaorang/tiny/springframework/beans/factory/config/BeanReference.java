package fun.xiaorang.tiny.springframework.beans.factory.config;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 21:38
 */
public class BeanReference {
  private final String beanName;

  public BeanReference(final String beanName) {
    this.beanName = beanName;
  }

  public String getBeanName() {
    return beanName;
  }
}
