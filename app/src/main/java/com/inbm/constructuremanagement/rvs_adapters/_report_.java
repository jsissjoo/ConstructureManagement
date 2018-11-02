package com.inbm.constructuremanagement.rvs_adapters;

import java.util.ArrayList;

public class _report_ {
    ArrayList<String> photos;
//    ArrayList<_questanswer_> questions;_
    ArrayList<_questions_> questions;

    public _report_(ArrayList<String> photos, ArrayList<_questions_> questions) {
        this.photos = photos;
        this.questions = questions;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<_questions_> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<_questions_> questions) {
        this.questions = questions;
    }

//    class _photos_{
//        String path;
//        String N;
//        String E;
//    }
    /*class _questanswer_{
        String quest;
        int answer;
    }*/
}

