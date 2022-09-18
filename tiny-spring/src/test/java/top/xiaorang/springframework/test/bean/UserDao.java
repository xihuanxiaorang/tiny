package top.xiaorang.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 5:52
 */
public class UserDao {
    private static final Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("1001", "小让");
        userMap.put("1002", "小星");
        userMap.put("1003", "小白");
    }

    public String queryUserName(String uId) {
        return userMap.get(uId);
    }
}
