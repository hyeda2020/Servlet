package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance(); // 싱글톤 패턴으로 리포지토리 관리

    /**
     *  테스트 코드들의 실행 순서가 보장이 안되기 때문에
     *  테스트 코드간 환경 이슈를 없애기 위해
     *  각 테스트가 끝나면 레포지토리를 초기화 해줌
     */
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("hello", 20);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findedMember = memberRepository.findById(savedMember.getId());
        assertThat(findedMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> resultList = memberRepository.findAll();

        // then
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList).contains(member1, member2);
    }
}
