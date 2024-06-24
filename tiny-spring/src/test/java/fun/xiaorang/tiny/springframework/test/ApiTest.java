package fun.xiaorang.tiny.springframework.test;

import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;
import fun.xiaorang.tiny.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:42
 */
public class ApiTest {
  @Test
  public void test_BeanFactory() {
    // 1. 初始化 BeanFactory
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. 注册 BeanDefinition
    final BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    beanFactory.registerBeanDefinition("userService", beanDefinition);

    // 3. 获取 Bean
    final UserService userService = (UserService) beanFactory.getBean("userService", "xiaorang");
    userService.queryUserInfo();
  }

  @Test
  public void test_cglib() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(UserService.class);
    enhancer.setCallback(new NoOp() {
      @Override
      public int hashCode() {
        return super.hashCode();
      }
    });
    final Object o = enhancer.create(new Class[]{String.class}, new Object[]{"xiaorang"});
    System.out.println(o);
  }

  @Test
  public void test_newInstance() throws InstantiationException, IllegalAccessException {
    final UserService userService = UserService.class.newInstance();
    System.out.println(userService);
  }

  @Test
  public void test_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    final Class<UserService> userServiceClass = UserService.class;
    final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
    final UserService userService = declaredConstructor.newInstance("xiaorang");
    System.out.println(userService);
  }

  @Test
  public void test_parameterTypes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    final Class<UserService> userServiceClass = UserService.class;
    final Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
    Constructor<?> constructorToUse = null;
    for (Constructor<?> constructor : declaredConstructors) {
      if (constructor.getParameterTypes().length == 1) {
        constructorToUse = constructor;
        break;
      }
    }
    final Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructorToUse.getParameterTypes());
    final UserService userService = declaredConstructor.newInstance("xiaorang");
    System.out.println(userService);
  }
}
