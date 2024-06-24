package fun.xiaorang.tiny.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 21:21
 */
public class PropertyValues {
  private final List<PropertyValue> propertyValues = new ArrayList<>();

  public void addPropertyValue(PropertyValue pv) {
    propertyValues.add(pv);
  }

  public List<PropertyValue> getPropertyValues() {
    return propertyValues;
  }
}
