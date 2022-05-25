package com.gmail.juliarusakevich.dao.entity.student;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private Boolean olympicGamer;

    public Student() {
    }

    @JsonCreator
    public Student(
            Integer id,
            String name,
            Integer age,
            Double score,
            Boolean olympicGamer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getOlympicGamer() {
        return olympicGamer;
    }

    public void setOlympicGamer(Boolean olympicGamer) {
        this.olympicGamer = olympicGamer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", score=" + score +
               ", olympicGamer=" + olympicGamer +
               '}';
    }

    public static class StudentBuilder {
        private Integer id;
        private String name;
        private Integer age;
        private Double score;
        private Boolean olympicGamer;

        StudentBuilder() {
        }

        public StudentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public StudentBuilder score(Double score) {
            this.score = score;
            return this;
        }

        public StudentBuilder olympicGamer(Boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public Student build() {
            return new Student(id, name, age, score, olympicGamer);
        }

        public String toString() {
            return "Student.StudentBuilder(id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", score=" + this.score + ", olympicGamer=" + this.olympicGamer + ")";
        }
    }
}
