package com.example.springpractice.service;
import com.example.springpractice.domain.Member;
import com.example.springpractice.repository.MemberRespository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRespository memberRespository;

    @AfterEach
    public void afterEach() {
        memberRespository.clearStore();
    }

    @Test
        //회원가입 서비스 테스트 : 정상
    void join() {
        //given : 무언가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when : 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야해!
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    //회원가입 예외
    public void checkForDuplicateMember() {
        //given : 무언가가 주어졌는데
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when : 이걸 실행했을때
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
