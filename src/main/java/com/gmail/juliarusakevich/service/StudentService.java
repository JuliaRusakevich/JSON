package com.gmail.juliarusakevich.service;

import com.gmail.juliarusakevich.core.dto.student.CreateStudentDto;
import com.gmail.juliarusakevich.core.dto.student.ReadStudentDto;
import com.gmail.juliarusakevich.dao.entity.student.Student;
import com.gmail.juliarusakevich.dao.exception.DAOException;
import com.gmail.juliarusakevich.dao.StudentDao;
import com.gmail.juliarusakevich.service.api.IStudent;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.mapper.CreateStudentMapper;
import com.gmail.juliarusakevich.service.validator.ValidationException;

import com.gmail.juliarusakevich.service.validator.CreateStudentValidator;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class StudentService implements IStudent {

    private static final StudentService INSTANCE = new StudentService();
    private final StudentDao studentDao = StudentDao.getInstance();
    private final CreateStudentMapper mapper = CreateStudentMapper.getInstance();

    private final CreateStudentValidator validator = CreateStudentValidator.getInstance();

    private StudentService() {
    }

    @Override
    public List<ReadStudentDto> find() throws ServiceException {
        try {
            return studentDao.findAll().stream()
                    .map(entity -> new ReadStudentDto(
                            entity.getId(),
                            entity.getName(),
                            entity.getAge(),
                            entity.getScore(),
                            entity.getOlympicGamer()
                    ))
                    .collect(toList());
        } catch (DAOException e) {
            throw new ServiceException("Unable return all students.", e);
        }
    }

    @Override
    public void save(CreateStudentDto dto) throws ServiceException {
        validationDate(dto);
        var entity = mapper.mapFrom(dto);
        try {
            studentDao.save(entity);
        } catch (DAOException e) {
            throw new ServiceException("Unable add students.", e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            studentDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Unable delete student.", e);
        }
    }

    @Override
    public void updateStudentInfo(Integer id, ReadStudentDto dto) throws ServiceException {
        try {
            studentDao.update(id, Student.builder()
                    .id(id)
                    .name(dto.getName())
                    .age(dto.getAge())
                    .score(dto.getScore())
                    .olympicGamer(dto.getOlympicGamer())
                    .build());
        } catch (DAOException e) {
            throw new ServiceException("Unable update student info.", e);
        }
    }

    private void validationDate(CreateStudentDto studentDto) {
        var result = validator.isValid(studentDto);
        if (!result.isValid()) {
            throw new ValidationException(result.getErrors());
        }
    }

    public static StudentService getInstance() {
        return INSTANCE;
    }
}
