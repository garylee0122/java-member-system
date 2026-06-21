package com.gary.membersystem.model;

public class Member {

    private Long id;

    private String name;

    private Integer points;

    private String level;

    public Member() {
    }

    public Member(Long id,
                  String name,
                  Integer points,
                  String level) {

        this.id = id;
        this.name = name;
        this.points = points;
        this.level = level;
    }

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}