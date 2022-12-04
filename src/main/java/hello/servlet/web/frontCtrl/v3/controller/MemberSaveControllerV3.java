package hello.servlet.web.frontCtrl.v3.controller;

import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        int age = Integer.parseInt(paramMap.get("age"));
        String username = paramMap.get("username");

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;

    }

}
