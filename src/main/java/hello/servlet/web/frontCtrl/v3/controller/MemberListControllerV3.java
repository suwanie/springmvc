package hello.servlet.web.frontCtrl.v3.controller;

import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");

        mv.getModel().put("Members", members);

        return mv;
    }

}
