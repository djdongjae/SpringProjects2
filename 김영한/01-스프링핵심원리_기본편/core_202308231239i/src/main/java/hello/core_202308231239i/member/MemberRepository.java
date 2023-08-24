package hello.core_202308231239i.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
