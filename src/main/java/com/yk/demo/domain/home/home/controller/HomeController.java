package com.yk.demo.domain.home.home.controller;

import org.springframework.stereotype.Controller;

@Controller
@Tag(name = "HomeController", description = "홈 컨트롤러")
@RequestMapping(value = "", produces = TEXT_HTML_VALUE)
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    @Operation(summary = "메인 페이지")
    public String showMain() {
        if (true) {
            throw new GlobalException("404-1", "존재하지 않는 페이지입니다.");
        }

        return "안녕";
    }
}