package hello.core_202308231239i.discount;

import hello.core_202308231239i.member.Member;

public interface DiscountPolicy {

    // @return 할인 대상 금액
    int discount(Member member, int price);
}
