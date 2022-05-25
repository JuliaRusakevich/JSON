package com.gmail.juliarusakevich.core.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Objects;

/**
 * 1.1 Порядковый номер (int)
 * 1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 1.3 возраст (7-17)
 * 1.4 оценка(0.0-10.0)
 * 1.5 признак участия в олимпиадах (bool).
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonDeserialize(builder = ReadStudentDto.ReadStudentDtoBuilder.class)
public class ReadStudentDto implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private Boolean olympicGamer;

    public ReadStudentDto(Integer id, String name, Integer age, Double score, Boolean olympicGamer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public static ReadStudentDtoBuilder builder() {
        return new ReadStudentDtoBuilder();
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
        if (!(o instanceof ReadStudentDto)) return false;
        ReadStudentDto that = (ReadStudentDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getAge(), that.getAge()) && Objects.equals(getScore(), that.getScore()) && Objects.equals(getOlympicGamer(), that.getOlympicGamer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getScore(), getOlympicGamer());
    }

    @Override
    public String toString() {
        return "ReadStudentDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", score=" + score +
               ", olympicGamer=" + olympicGamer +
               '}';
    }

    public static class ReadStudentDtoBuilder {
        private Integer id;
        private String name;
        private Integer age;
        private Double score;
        private Boolean olympicGamer;

        ReadStudentDtoBuilder() {
        }
        @JsonProperty(value = "id")
        public ReadStudentDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        @JsonProperty(value = "name")
        public ReadStudentDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty(value = "age")
        public ReadStudentDtoBuilder age(Integer age) {
            this.age = age;
            return this;
        }
        @JsonProperty(value = "score")
        public ReadStudentDtoBuilder score(Double score) {
            this.score = score;
            return this;
        }
        @JsonProperty(value = "olympic_gamer")
        public ReadStudentDtoBuilder olympicGamer(Boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public ReadStudentDto build() {
            return new ReadStudentDto(id, name, age, score, olympicGamer);
        }

        public String toString() {
            return "ReadStudentDto.ReadStudentDtoBuilder(id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", score=" + this.score + ", olympicGamer=" + this.olympicGamer + ")";
        }
    }
}
