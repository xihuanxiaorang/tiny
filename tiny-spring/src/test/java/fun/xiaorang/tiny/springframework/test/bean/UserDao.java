package fun.xiaorang.tiny.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 22:02
 */
public class UserDao {
  private static final Map<String, String> hashMap = new HashMap<>();

  static {
    hashMap.put("10001", "xiaorang");
    hashMap.put("10002", "sxy");
    hashMap.put("10003", "sanshi");
  }

  public String queryUserName(String userId) {
    return hashMap.get(userId);
  }
}
