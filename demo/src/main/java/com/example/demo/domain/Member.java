package com.example.demo.domain;

public class Member {

    // 유저가 정하는 것이 아님. 시스템 상에서 자동으로
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
