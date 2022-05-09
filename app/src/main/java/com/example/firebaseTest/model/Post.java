package com.example.firebaseTest.model;

public class Post {

    private String author; // 작성자
    private String title; // 제목
    private String content; // 내용
    private String contentPicURL; // 내용 사진
    private String createdAt; // 작성일

    public Post() {}

    public Post(String author, String title, String content) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

}
