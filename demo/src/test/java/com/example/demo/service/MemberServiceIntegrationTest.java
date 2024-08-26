package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 테스트 케이스에 달면, 트랙잭션을 실행하고 테스트가 끝나면 롤백해줌(db에 실제로 반영하지 않음) -> 반복적으로 테스트 실행 가능
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;

    // 구현체는 config 파일에서 불러올것임
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given : 뭔가가 주어짐
        Member member = new Member();
        member.setName("spring");

        //when : 실행되었을 때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 2번째 인자의 함수가 실행되어야 하는데 1번째 인자의 에러가 발생해야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}