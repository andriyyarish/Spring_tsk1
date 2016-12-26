package springPuzzlers.quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by Newman on 12/23/16.
 */
// configure beans using properties file
public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext (String fileName){
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int i = reader.loadBeanDefinitions(fileName);
        System.out.println("found " + i + "beans" );
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext propertyFileApplicationContext = new PropertyFileApplicationContext("context.properties");
        propertyFileApplicationContext.getBean(Quoter.class).sayQuote();
    }
}
