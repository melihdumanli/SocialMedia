package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {

    // If variable names are different, mapping is required.
    // @Mapping(source = "email",target = "username")
    @Mapping(source = "email",target = "username")
    @Mapping(source = "password",target = "password")
    User toUser(RegisterRequestDto dto);
    // Variable names are same. Mapping is not required.
    DoLoginResponseDto toDoLoginResponseDto(User user);


}
