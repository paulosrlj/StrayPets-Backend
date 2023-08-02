package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.api.dto.output.OutputUser;
import com.paulosrlj.straypets.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OutputUser convertToOutput(User user) {
        return modelMapper.map(user, OutputUser.class);
    }


}
