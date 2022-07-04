package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        ac.getBean(lifeCycleConfig.class);
        ac.close();
    }

    @Configuration
    static class lifeCycleConfig{

        @Bean
        //@Bean(initMethod = "init",destroyMethod = "destroy") 초기화, 소멸 전 메소드를 사용자가 직접 지정
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient(); //생성 과정
            networkClient.setUrl("http://hello-spring.dev"); //초기화 과정
            return networkClient;
        }
    }

}
