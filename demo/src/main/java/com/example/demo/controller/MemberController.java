package com.example.demo.controller;

import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// 스프링 컨테이너가, 스프링이 처음 뜰 때 스프링 컨테이너라는 통이 생기는데
// @controller라는 annotation이 있으면
// member controller라는 객체를 생성하여 스프링 그 안에 넣어줌
public class MemberController {
    private final MemberService memberService;

    // autowired : 스프링이 실행되면서 컨트롤러가 실행될 때, 생성자 실행
    // 이 때 컨테이너에 있는 서비스를 자동으로 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
