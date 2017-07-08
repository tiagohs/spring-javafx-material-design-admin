package com.tiagohs.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
	
	@GetMapping("/")
    public String home1() {
        return "/home";
    }
	
	@GetMapping("/")
    public String home() {
        return "/home";
    }
	
}
