package hello.servlet.web.frontcontroller.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.Controller;

import java.util.List;
import java.util.Map;

public class MemberListController implements Controller {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        List<Member> members = memberRepository.findAll();
        model.put("members", members); // 모델에 조회한 모든 멤버들의 리스트를 담음

        return "members"; // viewPath 리턴
    }
}
