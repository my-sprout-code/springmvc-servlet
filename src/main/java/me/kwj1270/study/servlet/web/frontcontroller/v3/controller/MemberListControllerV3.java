package me.kwj1270.study.servlet.web.frontcontroller.v3.controller;

import me.kwj1270.study.servlet.domain.member.Member;
import me.kwj1270.study.servlet.domain.member.MemberRepository;
import me.kwj1270.study.servlet.web.frontcontroller.ModelView;
import me.kwj1270.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.model().put("members", members);
        return modelView;
    }
}
