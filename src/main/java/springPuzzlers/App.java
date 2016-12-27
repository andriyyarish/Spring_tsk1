package springPuzzlers;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import springPuzzlers.quoters.Quoter;

/**
 * Created by Andriy_Yarish on 12/23/2016.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("puzzlers/SpringPuzzlers.xml");
        context.getBean(Quoter.class).sayQuote();

    }

    // create bin
    // define bean in spring xml
    // beanFactoryPostProcessor could change bean class before creation
    // Algoritm xml -> bean definitions created -> BeanFactoryPostProcessor works on beanDefinitions ->
    // BeanPostProcessors creates -> Beans creates -> IOC container became ready


}
