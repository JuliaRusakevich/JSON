package com.gmail.juliarusakevich.core.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Objects;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonDeserialize(builder = ReadGroupDto.ReadGroupDtoBuilder.class)
public class ReadGroupDto implements Serializable {

    private static final long serialVersionUID = 3L;

    private Integer id;
    private String groupName;

    public ReadGroupDto(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public static ReadGroupDtoBuilder builder() {
        return new ReadGroupDtoBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadGroupDto)) return false;
        ReadGroupDto that = (ReadGroupDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getGroupName(), that.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroupName());
    }

    @Override
    public String toString() {
        return "ReadGroupDto{" +
               "id=" + id +
               ", groupName='" + groupName + '\'' +
               '}';
    }

    public static class ReadGroupDtoBuilder {
        private Integer id;
        private String groupName;

        ReadGroupDtoBuilder() {
        }

        @JsonProperty(value = "id", defaultValue = "0")
        public ReadGroupDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        @JsonProperty(value = "group")
        public ReadGroupDtoBuilder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public ReadGroupDto build() {
            return new ReadGroupDto(id, groupName);
        }

        public String toString() {
            return "ReadGroupDto.ReadGroupDtoBuilder(id=" + this.id + ", groupName=" + this.groupName + ")";
        }
    }
}
