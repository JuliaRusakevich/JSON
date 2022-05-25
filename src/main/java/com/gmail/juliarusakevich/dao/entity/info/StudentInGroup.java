package com.gmail.juliarusakevich.dao.entity.info;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = StudentInGroup.StudentInGroupBuilder.class)
public class StudentInGroup {
    private String groupName;
    private List<ReadStudentInGroup> list;

    public StudentInGroup(String groupName, List<ReadStudentInGroup> list) {
        this.groupName = groupName;
        this.list = list;
    }

    public static StudentInGroupBuilder builder() {
        return new StudentInGroupBuilder();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ReadStudentInGroup> getList() {
        return list;
    }

    public void setList(List<ReadStudentInGroup> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentInGroup)) return false;
        StudentInGroup group = (StudentInGroup) o;
        return Objects.equals(getGroupName(), group.getGroupName()) && Objects.equals(getList(), group.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(), getList());
    }

    @Override
    public String toString() {
        return "StudentInGroup{" +
               "groupName='" + groupName + '\'' +
               ", list=" + list +
               '}';
    }

    public static class StudentInGroupBuilder {
        private String groupName;
        private List<ReadStudentInGroup> list;

        StudentInGroupBuilder() {
        }

        public StudentInGroupBuilder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public StudentInGroupBuilder list(List<ReadStudentInGroup> list) {
            this.list = list;
            return this;
        }

        public StudentInGroup build() {
            return new StudentInGroup(groupName, list);
        }

        public String toString() {
            return "StudentInGroup.StudentInGroupBuilder(groupName=" + this.groupName + ", list=" + this.list + ")";
        }
    }
}
/*

    private String groupName;
    private String studentName;
    private Double studentScore;
    private Boolean olympicGamer;

    public static StudentInGroupBuilder builder() {
        return new StudentInGroupBuilder();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
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
        if (!(o instanceof StudentInGroup)) return false;
        StudentInGroup that = (StudentInGroup) o;
        return Objects.equals(getGroupName(), that.getGroupName()) && Objects.equals(getStudentName(), that.getStudentName()) && Objects.equals(getStudentScore(), that.getStudentScore()) && Objects.equals(getOlympicGamer(), that.getOlympicGamer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(), getStudentName(), getStudentScore(), getOlympicGamer());
    }

    @Override
    public String toString() {
        return "StudentInGroup{" +
               "groupName='" + groupName + '\'' +
               ", studentName='" + studentName + '\'' +
               ", studentScore=" + studentScore +
               ", olympicGamer=" + olympicGamer +
               '}';
    }

    public StudentInGroup(String groupName, String studentName, Double studentScore, Boolean olympicGamer) {
        this.groupName = groupName;
        this.studentName = studentName;
        this.studentScore = studentScore;
        this.olympicGamer = olympicGamer;


    }

    public static class StudentInGroupBuilder {
        private String groupName;
        private String studentName;
        private Double studentScore;
        private Boolean olympicGamer;

        StudentInGroupBuilder() {

        }

        @JsonCreator
        public StudentInGroupBuilder(
                @JsonProperty(value = "group") String groupName,
                @JsonProperty(value = "student") String studentName,
                @JsonProperty(value = "score") Double studentScore,
                @JsonProperty(value = "olympic_gamer") Boolean olympicGamer) {
            this.groupName = groupName;
            this.studentName = studentName;
            this.studentScore = studentScore;
            this.olympicGamer = olympicGamer;
        }

        public StudentInGroup build() {
            return new StudentInGroup(groupName, studentName, studentScore, olympicGamer);
        }

        public String toString() {
            return "StudentInGroup.StudentInGroupBuilder(groupName=" + this.groupName + ", studentName=" + this.studentName + ", studentScore=" + this.studentScore + ", olympicGamer=" + this.olympicGamer + ")";
        }
    }
 */