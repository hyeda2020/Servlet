package hello.servlet.web.frontcontroller.controller;

import hello.servlet.web.frontcontroller.Controller;
import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormController implements Controller {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // viewPath 리턴
    }
}
