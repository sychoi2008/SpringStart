package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // 유저가 정하는 것이 아님. 시스템 상에서 자동으로
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //db에 있는 username이란 컬럼과 맵핑됨
    @Column(name = "username")
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
