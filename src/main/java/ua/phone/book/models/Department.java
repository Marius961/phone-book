package ua.phone.book.models;

import java.util.List;

public class Department {

    private int id;
    private String name;
    private int emploeeCount;

    public int getEmploeeCount() {
        return emploeeCount;
    }

    public void setEmploeeCount(int emploeeCount) {
        this.emploeeCount = emploeeCount;
    }

    List<Emploee> emploeeList;

    public List<Emploee> getEmploeeList() {
        return emploeeList;
    }

    public void setEmploeeList(List<Emploee> emploeeList) {
        this.emploeeList = emploeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
