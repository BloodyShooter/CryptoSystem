package org.gvozdetscky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс где перенаправляют на страницы.
 */
@Controller
public class MyController {

    @RequestMapping(value = "/")
    public String showWelcomePage() {
        return "welcome";
    }

    @RequestMapping(value = "/downloadJar")
    public String showDownloadJarPage() {
        return "downloadJar";
    }

    @RequestMapping(value = "/decrypt")
    public String showDecryptPagePage() {
        return "decrypt";
    }

    @RequestMapping(value = "/encrypt")
    public String showEncryptPagePage() {
        return "encrypt";
    }
}
