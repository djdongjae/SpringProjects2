package hello.core_202308231239i;

import hello.core_202308231239i.member.Grade;
import hello.core_202308231239i.member.Member;
import hello.core_202308231239i.member.MemberService;
import hello.core_202308231239i.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memeberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
