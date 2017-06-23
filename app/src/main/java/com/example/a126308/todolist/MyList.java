package com.example.a126308.todolist;

import java.io.Serializable;

/**
 * Created by 126308 on 7/6/2017.
 */

public class MyList implements Serializable {

    private int id;
    private String title, list;
//    private boolean important;

    public MyList(int id, String title, String list) {
        this.id = id;
        this.title = title;
        this.list = list;
//        this.important = important;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getList(){
        return list;
    }

//    public boolean isImportant() {
//        return important;
//    }
}
