package com.eukolos.cryptoexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {
    @GetMapping("/")
    public String home(Model model){
        return "index";
    }
}
