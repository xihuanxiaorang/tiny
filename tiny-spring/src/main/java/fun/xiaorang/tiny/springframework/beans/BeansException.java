package fun.xiaorang.tiny.springframework.beans;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:16
 */
public class BeansException extends RuntimeException {
  public BeansException(final String message) {
    super(message);
  }

  public BeansException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
