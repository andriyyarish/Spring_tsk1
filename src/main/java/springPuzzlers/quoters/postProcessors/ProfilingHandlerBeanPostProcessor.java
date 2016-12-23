package springPuzzlers.quoters.postProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.management.ManagementFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import springPuzzlers.quoters.ProfilingControler;
import springPuzzlers.quoters.anotations.Profiling;

/**
 * Created by Andriy_Yarish on 12/23/2016.
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    private ProfilingControler controler = new ProfilingControler();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controler,new ObjectName("profiling","name", "controler"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> aClass = o.getClass();
        if (aClass.isAnnotationPresent(Profiling.class)){
            map.put(s,aClass);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        Class aClass = map.get(s);
        if (aClass != null) {
            return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (controler.isEnabled()) {
                        System.out.println("PROFILING");
                        long before = System.nanoTime();
                        Object retval = method.invoke(o, args);
                        long after = System.nanoTime();
                        System.out.println(after - before);
                        System.out.println("END");
                        return retval;
                    }else {
                        return method.invoke(o,args);
                    }
                }
            });
        }
     return o;
    }
}
