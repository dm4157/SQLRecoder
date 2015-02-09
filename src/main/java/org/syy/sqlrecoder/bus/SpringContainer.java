package org.syy.sqlrecoder.bus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 维护spring容器
 * 第一次这么用，慢慢尝试吧
 * 且行且珍惜
 * Created by Administrator on 2015/2/9.
 */
public class SpringContainer {

    private ApplicationContext context;

    /**
     * 单例一个
     */
    private SpringContainer() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    private static class SpringContainerHandler{
        private static SpringContainer instance = new SpringContainer();
    }

    /**
     * 实例化
     * @return
     */
    public static SpringContainer me() {
        return SpringContainerHandler.instance;
    }

    /**
     * 获得spring上下文
     * @return
     */
    public ApplicationContext context() {
        return context;
    }

    /**
     * 根据类型获得bean
     * @param classType
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> classType) {
        return context.getBean(classType);
    }

    /**
     * 根据名字获得bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 根据名字和类型获得bean
     * 避免强类型转化
     * @param beanName
     * @param classType
     * @param <T>
     * @return
     */
    public <T> T getBean(String beanName, Class<T> classType) {
        return context.getBean(beanName, classType);
    }
}
