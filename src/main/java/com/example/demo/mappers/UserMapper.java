package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserMsDto;
import com.example.demo.entities.User;

//To satisfy the spring Dependency Injection we add componentModel = "Spring"
@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE  = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	@Mapping(source = "email", target = "emailaddress") //as in User class we have email & in USerMsDto we have email address which as not mapping correctly so we
	//mentioned source & target
	UserMsDto userToUserMsDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> userList);
}
