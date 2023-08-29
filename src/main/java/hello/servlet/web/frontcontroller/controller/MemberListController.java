package hello.servlet.web.frontcontroller.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.Controller;
import hello.servlet.web.frontcontroller.ModelView;

import java.util.List;
import java.util.Map;

public class MemberListController implements Controller {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members); // 모델에 조회한 모든 멤버들의 리스트를 담음

        return mv;
    }
}
