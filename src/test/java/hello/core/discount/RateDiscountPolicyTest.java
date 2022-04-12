package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용 됩니다.")
    void vip_o(){
        Member memberVip = new Member("memberVip", 1L, Grade.VIP);
        int discountAmount = discountPolicy.discount(memberVip, 10000);
        assertThat(discountAmount).isEqualTo(1000);
    }
    @Test
    void vip_x(){
        Member memberBasic = new Member("memberBasic", 1L, Grade.BASIC);
        int discountAmount = discountPolicy.discount(memberBasic, 10000);
        assertThat(discountAmount).isEqualTo(0);
    }
}