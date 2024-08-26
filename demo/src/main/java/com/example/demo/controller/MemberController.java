package com.example.demo.controller;


import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new") //url을 통해 직접 치는 것
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        System.out.println("form.getName() = " + form.getName());
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원가입이 끝나면 홈 화면으로 보내버림
        return "redirect:/";

    }

    /*
    post와 get의 url이 같더라도 다르게 맵핑할 수 있음
     */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // list가 model에 담김
        return "members/memberList";
    }

}
