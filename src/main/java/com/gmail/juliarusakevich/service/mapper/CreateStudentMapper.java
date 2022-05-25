package com.gmail.juliarusakevich.service.mapper;

import com.gmail.juliarusakevich.core.dto.student.CreateStudentDto;
import com.gmail.juliarusakevich.dao.entity.student.Student;
import com.gmail.juliarusakevich.service.mapper.api.IMapper;

public class CreateStudentMapper implements IMapper<CreateStudentDto, Student> {

    private static final CreateStudentMapper INSTANCE = new CreateStudentMapper();

    private CreateStudentMapper() {
    }

    @Override
    public Student mapFrom(CreateStudentDto object) {
        return Student.builder()
                .name(object.getName())
                .age(object.getAge())
                .score(object.getScore())
                .olympicGamer(object.isOlympicGamer())
                .build();
    }

    public static CreateStudentMapper getInstance() {
        return INSTANCE;
    }
}
