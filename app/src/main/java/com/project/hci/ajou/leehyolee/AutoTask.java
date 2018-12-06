package com.project.hci.ajou.leehyolee;

import java.util.ArrayList;

public class AutoTask {
    private String autolist[] = {
            "경제뉴스 3개 읽기!!",
            "코리아 헤럴드 3개 읽기!!",
            "영어 단어 10개 외우기!!",
            "책 5장 읽기!!",
            "윗몸 일으키기 100회!!",
            "팔굽혀펴기 100회!!",
            "명상하기!!",
            "아파트 한바퀴 걷기!!",
            "일정 정리하기!!"
    };

    public String getTask() {
        int len = autolist.length;
        double random = Math.random();
        int value = (int)(random * len);
        return autolist[value];
    }

    public AutoTask() {    }
}
