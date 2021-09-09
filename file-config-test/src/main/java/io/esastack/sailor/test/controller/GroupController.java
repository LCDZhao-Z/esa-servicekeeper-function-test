package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.Group;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/group")
public class GroupController {


    @Group("groupA")
    @GetMapping("/groupA")
    @ResponseBody
    public String group() throws InterruptedException {
        Thread.sleep(1000);
        return "groupA";
    }

}
