package me.kwj1270.study.servlet.web.frontcontroller.v3.controller;

import me.kwj1270.study.servlet.domain.member.Member;
import me.kwj1270.study.servlet.domain.member.MemberRepository;
import me.kwj1270.study.servlet.web.frontcontroller.ModelView;
import me.kwj1270.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.model().put("member", member);
        return modelView;
    }
}
