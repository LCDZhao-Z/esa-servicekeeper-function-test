package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupComponent {

    @Group("groupA")
    public String group() throws InterruptedException {
        Thread.sleep(1000);
        return "groupA";
    }

}
