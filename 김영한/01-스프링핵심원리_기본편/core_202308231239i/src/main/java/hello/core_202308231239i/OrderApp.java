package hello.core_202308231239i;

import hello.core_202308231239i.member.Grade;
import hello.core_202308231239i.member.Member;
import hello.core_202308231239i.member.MemberService;
import hello.core_202308231239i.member.MemberServiceImpl;
import hello.core_202308231239i.order.Order;
import hello.core_202308231239i.order.OrderService;
import hello.core_202308231239i.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
