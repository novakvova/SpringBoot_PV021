package org.example.mapper;

import org.example.dto.userdto.UserItemDTO;
import org.example.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    UserItemDTO userToUserItemDTO(User user);
    List<UserItemDTO> usersToUserItemDTO_List(List<User> users);
}
