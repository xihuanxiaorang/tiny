package top.xiaorang.springframework.test.bean;

import top.xiaorang.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 5:52
 */
@Component
public class UserDao implements IUserDao {
    private static final Map<String, String> userMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行：init-method");
        userMap.put("1001", "小让");
        userMap.put("1002", "小星");
        userMap.put("1003", "小白");
    }

    public void destroyDataMethod() {
        System.out.println("执行：destroy-method");
        userMap.clear();
    }

    public String queryUserName(String uId) {
        return userMap.get(uId);
    }
}
