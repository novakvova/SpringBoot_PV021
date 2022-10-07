package org.example.mapper;

import org.example.DTO.UserDTO.UserCreateDTO;
import org.example.DTO.UserDTO.UserItemDTO;
import org.example.DTO.order.OrderItemDTO;
import org.example.DTO.product.ProductItemDTO;
import org.example.entities.OrderEntity;
import org.example.entities.ProductEntity;
import org.example.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    UserItemDTO userItemDTO(UserEntity user);
    List<UserItemDTO> usersItemDTO_List(List<UserEntity> users);
    UserEntity userCreateDtoToUserEntity(UserCreateDTO user);
    List<ProductItemDTO> productToProductItemDTO_List(List<ProductEntity> products);
    List<OrderItemDTO> orderToOrderItemDTO_List(List<OrderEntity> orders);

}
