package top.xiaorang.springframework.util;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/22 2:20
 */
public interface StringValueResolver {
    /**
     * Resolve the given String value, for example parsing placeholders.
     *
     * @param strVal the original String value (never {@code null})
     * @return the resolved String value (may be {@code null} when resolved to a null
     * value), possibly the original String value itself (in case of no placeholders
     * to resolve or when ignoring unresolvable placeholders)
     * @throws IllegalArgumentException in case of an unresolvable String value
     */
    String resolveStringValue(String strVal);
}
