package fun.xiaorang.tiny.springframework.test.bean;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:43
 */
public class UserService {
  private String userId;
  private UserDao userDao;

  public String queryUserInfo() {
    return userDao.queryUserName(userId);
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }
}
