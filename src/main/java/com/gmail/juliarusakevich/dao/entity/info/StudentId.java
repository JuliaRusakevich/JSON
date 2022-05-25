package com.gmail.juliarusakevich.dao.entity.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class StudentId implements Serializable {

    private static final long serialVersionUID = 2L;

    private final Integer studentId;

    @JsonCreator
    public StudentId(
            @JsonProperty("student_id") Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentId)) return false;
        StudentId studentId1 = (StudentId) o;
        return Objects.equals(getStudentId(), studentId1.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId());
    }

    @Override
    public String toString() {
        return "StudentId{" +
               "studentId=" + studentId +
               '}';
    }

    public static StudentIdBuilder builder() {
        return new StudentIdBuilder();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public static class StudentIdBuilder {
        private Integer studentId;

        StudentIdBuilder() {
        }

        public StudentIdBuilder studentId(Integer studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentId build() {
            return new StudentId(studentId);
        }

        public String toString() {
            return "StudentId.StudentIdBuilder(studentId=" + this.studentId + ")";
        }
    }
}
