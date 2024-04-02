package com.myshopping.MyShopping.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderProductDTO {
    UUID productID;
    String productName;
    int quantity;
}
