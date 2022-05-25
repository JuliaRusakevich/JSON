package com.gmail.juliarusakevich.service.mapper;

import com.gmail.juliarusakevich.core.dto.group.CreateGroupDto;
import com.gmail.juliarusakevich.dao.entity.group.Group;
import com.gmail.juliarusakevich.service.mapper.api.IMapper;

public class CreateGroupMapper implements IMapper<CreateGroupDto, Group> {

    private static final CreateGroupMapper INSTANCE = new CreateGroupMapper();

    private CreateGroupMapper() {
    }

    @Override
    public Group mapFrom(CreateGroupDto object) {
        return Group.builder()
                .groupName(object.getGroupName())
                .build();
    }

    public static CreateGroupMapper getInstance() {
        return INSTANCE;
    }
}
