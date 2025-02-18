package com.tit.day1_csv.advance.merge_twoCSV;

public class Student {
    private int id;
    private String name;
    private int age;
    private int marks;
    private String grade;

    //Constructor for merging both files
    public Student(int id, String name, int age, int marks, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    //toString() method for easy printing
    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + marks + "," + grade;
    }
}
