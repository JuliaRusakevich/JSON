package com.gmail.juliarusakevich.dao.entity.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public final class InfoGroup implements Serializable {

    private final Integer groupId;
    private final List<StudentId> list;

    @JsonCreator
    InfoGroup(
            @JsonProperty("group_id") Integer groupId,
            @JsonProperty("students") List<StudentId> list) {
        this.groupId = groupId;
        this.list = list;
    }

    public static InfoGroupBuilder builder() {
        return new InfoGroupBuilder();
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public List<StudentId> getList() {
        return this.list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoGroup)) return false;
        InfoGroup infoGroup = (InfoGroup) o;
        return Objects.equals(getGroupId(), infoGroup.getGroupId()) && Objects.equals(getList(), infoGroup.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getList());
    }

    public String toString() {
        return "InfoGroup(groupId=" + this.getGroupId() + ", list=" + this.getList() + ")";
    }

    public static class InfoGroupBuilder {
        private Integer groupId;
        private List<StudentId> list;

        InfoGroupBuilder() {
        }

        public InfoGroupBuilder groupId(Integer groupId) {
            this.groupId = groupId;
            return this;
        }

        public InfoGroupBuilder list(List<StudentId> list) {
            this.list = list;
            return this;
        }

        public InfoGroup build() {
            return new InfoGroup(groupId, list);
        }

        public String toString() {
            return "InfoGroup.InfoGroupBuilder(groupId=" + this.groupId + ", list=" + this.list + ")";
        }
    }
}
