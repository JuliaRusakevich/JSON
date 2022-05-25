package com.gmail.juliarusakevich.core.dto.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateGroupDto {

    private String groupName;

    @JsonCreator
    public CreateGroupDto(@JsonProperty(value = "group") String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupDto)) return false;
        CreateGroupDto that = (CreateGroupDto) o;
        return Objects.equals(getGroupName(), that.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }

    @Override
    public String toString() {
        return "CreateGroupDto{" +
               "groupName='" + groupName + '\'' +
               '}';
    }
}
