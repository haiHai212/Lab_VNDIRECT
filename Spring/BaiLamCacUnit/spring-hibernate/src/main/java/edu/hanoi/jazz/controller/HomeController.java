package edu.hanoi.jazz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Hello Spring Hibernate");
        return mv;
    }

    @RequestMapping("/nguoi-dung")
    ModelAndView forUser() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "This is protected page!");
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/login")
    ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null) {
            mv.addObject("error", error);
            return mv;
        }
        return mv;
    }
}
