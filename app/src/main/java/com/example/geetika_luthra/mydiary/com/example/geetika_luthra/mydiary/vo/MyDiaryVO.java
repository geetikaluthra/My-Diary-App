package com.example.geetika_luthra.mydiary.com.example.geetika_luthra.mydiary.vo;

/**
 * Created by Geetika_Luthra on 17-03-2017.
 */

public class MyDiaryVO {
    private String tDate;
    private String Title;
    private String Body;

    public String getDate() {

        return tDate;
    }

    public void settDate(String date) {

        tDate = date;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {

        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}
