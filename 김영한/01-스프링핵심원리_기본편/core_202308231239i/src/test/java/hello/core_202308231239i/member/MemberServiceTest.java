package hello.core_202308231239i.member;

import hello.core_202308231239i.member.Grade;
import hello.core_202308231239i.member.Member;
import hello.core_202308231239i.member.MemberService;
import hello.core_202308231239i.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
