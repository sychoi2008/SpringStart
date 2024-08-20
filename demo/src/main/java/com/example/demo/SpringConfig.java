package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링이 실행되면서 configuration을 읽고 @Bean의 로직을 동작시켜서 빈에 등록
@Configuration
public class SpringConfig {

    // 스프링 빈을 등록할거야
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

