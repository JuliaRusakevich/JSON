package com.gmail.juliarusakevich.service.api;

import com.gmail.juliarusakevich.core.dto.group.CreateGroupDto;
import com.gmail.juliarusakevich.core.dto.group.ReadGroupDto;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.validator.ValidationException;

import java.util.List;

public interface IGroup {
    List<ReadGroupDto> find() throws ServiceException;

    void save(CreateGroupDto dto) throws ServiceException, ValidationException;

    void delete(Integer id) throws ServiceException;

    void updateGroupName(Integer id, String name) throws ServiceException;
}
