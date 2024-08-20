package com.example.springpractice.repository;
import com.example.springpractice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRespository repository = new MemoryMemberRespository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


     @Test
     //저장
     public void save() {
         Member member = new Member();
         member.setName("이수민");

         repository.save(member);

         Member result = repository.findById(member.getId()).get();
         assertThat(member).isEqualTo(result);
     }

     @Test
     public void findByName() {
         //이름 찾기 기능
         Member member1 = new Member();
         member1.setName("이수민1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("이수민2");
         repository.save(member2);

         Member result = repository.findByName("이수민1").get();

         assertThat(result).isEqualTo(member1);

     }

     @Test
     //전부 조회
    public void findAll() {
         Member member1 = new Member();
         member1.setName("이수민1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("이수민2");
         repository.save(member2);

         List<Member> result = repository.findAll();
            assertThat(result.size()).isEqualTo(2);
     }

}
