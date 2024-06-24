package fun.xiaorang.tiny.springframework.test.bean;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:43
 */
public class UserService {
  private String name;

  public UserService() {
  }

  public UserService(final String name) {
    this.name = name;
  }

  public void queryUserInfo() {
    System.out.println("查询用户信息：" + name);
  }

  @Override
  public String toString() {
    return "UserService{" + "name='" + name + '\'' + '}';
  }
}
