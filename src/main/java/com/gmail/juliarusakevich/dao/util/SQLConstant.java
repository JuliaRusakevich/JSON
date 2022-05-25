package com.gmail.juliarusakevich.dao.util;

public final class SQLConstant {

    public static final String FIND_ALL_GROUPS_SQL = "SELECT id, group_name FROM olympic_gamer.group";
    public static final String FIND_GROUP_BY_ID_SQL = FIND_ALL_GROUPS_SQL + "WHERE id = ?";
    public static final String SAVE_GROUP_SQL = "INSERT INTO olympic_gamer.group (group_name) VALUES (?)";
    public static final String DELETE_GROUP_SQL = "DELETE FROM olympic_gamer.group WHERE id = ?";
    public static final String UPDATE_GROUP_SQL = "UPDATE olympic_gamer.group SET group_name = ? WHERE id = ?";

    public static final String FIND_ALL_STUDENTS_SQL = "SELECT id, name, age, score, olympic_gamer FROM olympic_gamer.student";
    public static final String SAVE_STUDENT_SQL = "INSERT INTO olympic_gamer.student (name, age, score, olympic_gamer) VALUES (?, ?, ?, ?)";
    public static final String DELETE_STUDENT_SQL = "DELETE FROM olympic_gamer.student WHERE id = ?";
    public static final String UPDATE_STUDENT_SQL = "UPDATE olympic_gamer.student SET name = ?, age = ?, score = ?, olympic_gamer = ? WHERE id = ?";

    public static final String ADD_STUDENTS_TO_GROUP_SQL = "INSERT INTO olympic_gamer.group_info (group_id, student_id) VALUES (?, ?)";

    public static final String DELETE_STUDENTS_FROM_GROUP_SQL = "DELETE FROM olympic_gamer.group_info  WHERE student_id = ?";

    public static final String FIND_ALL_GROUPS_AND_STUDENTS_SQL = "SELECT group_name, name, score, olympic_gamer\n" +
                                                                  "from university.olympic_gamer.group as g\n" +
                                                                  "join olympic_gamer.group_info gi on g.id = gi.group_id\n" +
                                                                  "join olympic_gamer.student s on s.id = gi.student_id;";

    //  public static final String FIND_BY_GROUP_ID_SQL = FIND_ALL_GROUPS_AND_STUDENTS_SQL + "WHERE ID = ?";

    private SQLConstant() {
    }
}
