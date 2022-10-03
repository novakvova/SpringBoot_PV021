package org.example.mapper;

import org.example.dto.product.ProductItemDTO;
import org.example.dto.userdto.UserCreateDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.ProductEntity;
import org.example.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    UserItemDTO userToUserItemDTO(UserEntity user);
    List<UserItemDTO> usersToUserItemDTO_List(List<UserEntity> users);
    UserEntity userCreateDtoToUserEntity(UserCreateDTO user);
    ProductItemDTO productToProductItemDTO(ProductEntity product);
    List<ProductItemDTO> productToProductItemDTO_List(List<ProductEntity> products);
}
