package cxf.internals;

import org.apache.cxf.service.invoker.spring.SpringBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.Arrays;

public class UsingSpringBeanFactory {
    public static void main(String[] args) {
        File file = new File("./cxf/src/main/webapp/WEB-INF/cxfbeans.xml");
        System.out.println(file.getAbsolutePath());
        ApplicationContext context = new FileSystemXmlApplicationContext(file.getAbsolutePath());
        SpringBeanFactory springBeanFactory = new SpringBeanFactory("bus");
        springBeanFactory.setApplicationContext(context);
        Arrays.stream(context.getBeanDefinitionNames()).iterator().forEachRemaining(x -> System.out.println(x));
    }
}
