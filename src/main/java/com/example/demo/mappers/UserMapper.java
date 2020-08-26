package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserMsDto;
import com.example.demo.entities.User;

//To satisfy the spring Dependency Injection we add componentModel = "Spring"
@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE  = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	UserMsDto userToUserMsDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> userList);
}
