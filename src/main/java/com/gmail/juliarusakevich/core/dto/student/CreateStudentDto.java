package com.gmail.juliarusakevich.core.dto.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class CreateStudentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public CreateStudentDto() {
    }

    @JsonCreator
    public CreateStudentDto(
            @JsonProperty(value = "name") String name,
            @JsonProperty(value = "age") int age,
            @JsonProperty(value = "score") double score,
            @JsonProperty(value = "olympic_gamer") boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }


    public boolean isOlympicGamer() {
        return olympicGamer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateStudentDto)) return false;
        CreateStudentDto that = (CreateStudentDto) o;
        return getAge() == that.getAge() && Double.compare(that.getScore(), getScore()) == 0 && isOlympicGamer() == that.isOlympicGamer() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getScore(), isOlympicGamer());
    }

    @Override
    public String toString() {
        return "CreateStudentDto{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", score=" + score +
               ", olympicGamer=" + olympicGamer +
               '}';
    }
}
