package springPuzzlers.quoters.postProcessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import springPuzzlers.quoters.anotations.PostProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


//  All beans are already created when application listener starts


public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();

        for (String name : names) {

            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            
            try {

                Class<?> originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();

                for (Method method: methods) {
                    if(method.isAnnotationPresent(PostProxy.class)){
                        Object bean = context.getBean(name);
                        Method methodProxy = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        methodProxy.invoke(bean);
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
