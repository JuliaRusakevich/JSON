package com.gmail.juliarusakevich.dao.entity.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = ReadStudentInGroup.ReadStudetInGroupBuilder.class)
public class ReadStudentInGroup {

    private String studentName;
    private Double studentScore;
    private Boolean olympicGamer;

    public ReadStudentInGroup(String studentName, Double studentScore, Boolean olympicGamer) {
        this.studentName = studentName;
        this.studentScore = studentScore;
        this.olympicGamer = olympicGamer;
    }

    public static ReadStudetInGroupBuilder builder() {
        return new ReadStudetInGroupBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadStudentInGroup)) return false;
        ReadStudentInGroup that = (ReadStudentInGroup) o;
        return Objects.equals(studentName, that.studentName) && Objects.equals(studentScore, that.studentScore) && Objects.equals(olympicGamer, that.olympicGamer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentScore, olympicGamer);
    }

    @Override
    public String toString() {
        return "ReadStudentInGroup{" +
               "studentName='" + studentName + '\'' +
               ", studentScore=" + studentScore +
               ", olympicGamer=" + olympicGamer +
               '}';
    }

    public static class ReadStudetInGroupBuilder {
        private String studentName;
        private Double studentScore;
        private Boolean olympicGamer;

        ReadStudetInGroupBuilder() {
        }
        @JsonCreator
        public ReadStudetInGroupBuilder(
                @JsonProperty(value = "student") String studentName,
                @JsonProperty(value = "score") Double studentScore,
                @JsonProperty(value = "olympic_gamer") Boolean olympicGamer) {
            this.studentName = studentName;
            this.studentScore = studentScore;
            this.olympicGamer = olympicGamer;
        }

        public ReadStudetInGroupBuilder studentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public ReadStudetInGroupBuilder studentScore(Double studentScore) {
            this.studentScore = studentScore;
            return this;
        }

        public ReadStudetInGroupBuilder olympicGamer(Boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public ReadStudentInGroup build() {
            return new ReadStudentInGroup(studentName, studentScore, olympicGamer);
        }

        public String toString() {
            return "ReadStudentInGroup.ReadStudetInGroupBuilder(studentName=" + this.studentName + ", studentScore=" + this.studentScore + ", olympicGamer=" + this.olympicGamer + ")";
        }
    }
}
