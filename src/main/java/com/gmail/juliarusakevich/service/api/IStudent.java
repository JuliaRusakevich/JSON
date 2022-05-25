package com.gmail.juliarusakevich.service.api;

import com.gmail.juliarusakevich.core.dto.student.CreateStudentDto;
import com.gmail.juliarusakevich.core.dto.student.ReadStudentDto;
import com.gmail.juliarusakevich.service.exception.ServiceException;

import java.util.List;

public interface IStudent {

    List<ReadStudentDto> find() throws ServiceException;

    void save(CreateStudentDto dto) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void updateStudentInfo(Integer id, ReadStudentDto dto) throws ServiceException;
}
