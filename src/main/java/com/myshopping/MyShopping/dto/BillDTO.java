package com.myshopping.MyShopping.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillDTO {
    UUID billID;
    String buyerName;
    UUID buyerID;
    String emailID;
    int totalQuantity;
    int totalPrice;
    List<OrderProductDTO> orderProducts;
}
