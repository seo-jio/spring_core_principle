package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.spel.CodeFlow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletoneWithPrototype {

    @Test
    public void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count1).isEqualTo(1);
        Assertions.assertThat(count2).isEqualTo(1);
    }

    static class ClientBean {
//        private final PrototypeBean prototypeBean; //생성자 주입 시 사용

        @Autowired  //java에서 제공하는 DL 기능만 제공하는 Provider 사용
        private Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired //스프링에 의존적인 ObjectProvider 사용
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

//        생성자 주입시 사용
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

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
