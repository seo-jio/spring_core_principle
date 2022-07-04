package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor //롬복 사용
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    동일 타입의 빈이 많은 경우 생성자 주입 사용 시 필드명(변수명)으로 같은 타입의 빈을 구분하여 주입한다.
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

    @Autowired
    //MainDiscountPolicy라는 사용자 지정 annotation을 만들고 그 안에 Qualifier를 사용하여 빈을 지정하는 모습
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
