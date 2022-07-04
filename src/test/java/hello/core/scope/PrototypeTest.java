package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){ //빈 조회시 스프링 컨테이너가 프로토타입 빈을 생성한다. 추가로 종료 메서드는 호출 되지 않는다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("create prototype bean1");  //이 때 init이 불림
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("create prototype bean2");  //init이 한 번 더 불림(빈을 조회 할 때 마다 새로운 인스턴스를 생성하기 때문에)
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
