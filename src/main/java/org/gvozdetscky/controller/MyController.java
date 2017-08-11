package org.gvozdetscky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Egor on 10.08.2017.
 */
@Controller
public class MyController {

    @RequestMapping(value = "/")
    public String showHello() {
        return "hello.html";
    }

}
