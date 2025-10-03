package com.example.demo1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Table(name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Không được để trống tên")
    private String name;

    @Column(name="final_score")
    private double finalScore;

    public Student() {
    }

    public Student(int id, String name, double finalScore) {
        this.id = id;
        this.name = name;
        this.finalScore = finalScore;
    }

    public Student(String name,double finalScore) {
        this.name = name;
        this.finalScore = finalScore;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getFinalScore() {
        return finalScore;
    }
    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
