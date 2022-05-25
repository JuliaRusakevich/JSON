package com.gmail.juliarusakevich.dao.entity.group;

import java.io.Serializable;
import java.util.Objects;

public class Group implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer id;
    private String groupName;

    public Group() {
    }

    public Group(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public static GroupBuilder builder() {
        return new GroupBuilder();
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
        if (!(o instanceof Group)) return false;
        Group that = (Group) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getGroupName(), that.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroupName());
    }

    @Override
    public String toString() {
        return "Group{" +
               "id=" + id +
               ", groupName='" + groupName + '\'' +
               '}';
    }

    public static class GroupBuilder {
        private Integer id;
        private String groupName;

        GroupBuilder() {
        }

        public GroupBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public GroupBuilder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Group build() {
            return new Group(id, groupName);
        }

        public String toString() {
            return "Group.GroupBuilder(id=" + this.id + ", groupName=" + this.groupName + ")";
        }
    }
}
