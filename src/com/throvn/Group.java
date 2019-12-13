package com.throvn;

public class Group {
    private int id;
    private String name;
    private String desc;

    public Group(int id, String name, String desc) {
        this.id = id;
        this.desc = desc;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
}
