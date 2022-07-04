package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    public void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        Member member = new Member("seojio", 1l, Grade.VIP);
        DiscountService discountService = ac.getBean(DiscountService.class);

        int fixPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(fixPrice).isEqualTo(1000);
        int ratePrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(ratePrice).isEqualTo(2000);
    }

    @Component
    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        //DiscountPolicy 타입의 모든 빈들을 map과 리스트 형식으로 받아 옴
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies){
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);  //String으로 입력받은 값과 일치하는 이름을 갖는 빈을 불러옴
            return discountPolicy.discount(member, price);
        }
    }
}
