package com.inbm.constructuremanagement.rvs_adapters;

import java.util.ArrayList;

public class _questions_ {
    String _id;
    String __v;
    String quest;
    ArrayList<String> answers;
    String answer;

    public _questions_(String quest, ArrayList<String> answer) {
        this.quest = quest;
        this.answers = answer;
    }

    public _questions_(String quest, ArrayList<String> answers, String answer) {
        this.quest = quest;
        this.answers = answers;
        this.answer = answer;
    }

    public String getQuest() {
        return quest;
    }

    public ArrayList<String> getAnswer() {
        return answers;
    }
}
