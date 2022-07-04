package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("rateDiscountPolicy")
//동일 타입 빈이 여러 개 일 때 주로 사용하는 이름을 지정
//@Primary
//동일 타입 빈이 여러 개 일 경우 자주 사용하는 빈을 의존관계 주입 시 넣어주도록 설정
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountRate / 100;
        }
        return 0;
    }
}
