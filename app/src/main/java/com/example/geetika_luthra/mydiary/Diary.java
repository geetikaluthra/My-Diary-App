package com.example.geetika_luthra.mydiary;

/**
 * Created by Geetika_Luthra on 18-03-2017.
 */

public class Diary {


    String _date;
    String _title;
    String _body;

    // Empty constructor
    public Diary(){

    }
    // constructor
    public Diary(String date, String title, String body){
        this._date = date;
        this._title = title;
        this._body = body;
    }

    // constructor
//    public Diary(String name, String _phone_number){
//        this._name = name;
//        this._phone_number = _phone_number;
//    }


    // getting date
    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_body() {
        return _body;
    }

    public void set_body(String _body) {
        this._body = _body;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

}
