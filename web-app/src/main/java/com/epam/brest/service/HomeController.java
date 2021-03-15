package com.epam.brest.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller
 */
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }
}
