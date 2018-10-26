package com.example.jianshi.mydatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//this place is where table metadata is held

@Entity
public class Question {

    @PrimaryKey (autoGenerate = true)
    private int qid;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    @ColumnInfo(name = "question")
    private String Question;

    @ColumnInfo (name = "Answer")
    private String Answer;

    public Question(String Question, String Answer) {
        this.Question = Question;
        this.Answer = Answer;

    }


    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer= Answer;
    }


}
