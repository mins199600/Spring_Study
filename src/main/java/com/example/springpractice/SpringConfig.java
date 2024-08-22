package com.example.springpractice;

import com.example.springpractice.repository.JdbcMemberRepository;
import com.example.springpractice.repository.MemberRepository;
import com.example.springpractice.repository.MemoryMemberRespository;
import com.example.springpractice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
      //  return new MemoryMemberRespository();
        return new JdbcMemberRepository(dataSource);
    }
}
