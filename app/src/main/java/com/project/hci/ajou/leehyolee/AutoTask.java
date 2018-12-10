package com.project.hci.ajou.leehyolee;

import java.util.ArrayList;

public class AutoTask {
    private String autolist[] = {
            "Taking a walk",
            "Reading the K orea Herald",
            "Memorizing 10 english words",
            "Reading a book",
            "Doing 10 sit-ups",
            "Doing 10 push-ups",
            "Meditating",
            "Reading Internet News",
            "Code refactoring",
            "House cleaning"
    };

    public String getTask() {
        int len = autolist.length;
        double random = Math.random();
        int value = (int)(random * len);
        return autolist[value];
    }

    public AutoTask() {    }
}
