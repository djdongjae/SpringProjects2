package hello.core_202308231239i.order;

import hello.core_202308231239i.discount.DiscountPolicy;
import hello.core_202308231239i.discount.FixDiscountPolicy;
import hello.core_202308231239i.member.Member;
import hello.core_202308231239i.member.MemberRepository;
import hello.core_202308231239i.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
