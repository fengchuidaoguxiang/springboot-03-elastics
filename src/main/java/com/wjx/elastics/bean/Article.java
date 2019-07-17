package com.wjx.elastics.bean;

//import io.searchbox.annotations.JestId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

//    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;
}
