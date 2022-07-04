package hello.core.beanfind;


import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.DoubleStream;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAll(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        System.out.println("ApplicationContextInfoTest.findAll");
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "Object" + bean);
        }
    }

    //개발자가 등록한 어플리케이션 bean만 출력
    @Test
    void findAllApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        System.out.println("ApplicationContextInfoTest.findAllApplicationBean");
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//BeanDefinition 정보를 받아옴

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + "Object" + bean);
            }

        }
    }

    @Test
    @DisplayName("Application Bean만 출력해보기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
    }
}
