package com.myshopping.MyShopping.service;

import com.myshopping.MyShopping.dto.OrderDetailsDTO;
import com.myshopping.MyShopping.models.AppUser;
import com.myshopping.MyShopping.models.OrderTable;
import com.myshopping.MyShopping.models.Product;
import com.myshopping.MyShopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MailService mailService;

    public OrderTable placeOrder(List<OrderDetailsDTO> orderDetailsDTOList, UUID userId){
        // Get user by id
        // 1. Verify user who is placing order
        // 2. We are forgetting to decrease packets in the database.
        // 3. Create bill dto
        // 4. email service
        AppUser user = userService.getUserById(userId);
        OrderTable orderTable = new OrderTable();
        // Get List of product
        // we need to get products by id
        int totalPrice = 0;
        int totalQuantity = 0;
        List<Product> products = new ArrayList<>();
        for(OrderDetailsDTO order : orderDetailsDTOList){
            UUID productId = order.getId();
            totalQuantity += order.getQuantity();
            Product product = productService.getProductById(productId);
            totalPrice += (order.getQuantity()*product.getPrice());
            products.add(product);
            orderTable.setPaymentMode(order.getPaymentMode());

        }
        orderTable.setProducts(products);
        orderTable.setUser(user);
        orderTable.setStatus("Not Delivered");
        orderTable.setTotalPrice(totalPrice);
        orderTable.setTotalQuantity(totalQuantity);

        orderRepository.save(orderTable);

        mailService.sendMail("Hey, order got placed", user.getEmail(), "Congratulations !! order placed");

        return orderTable;



    }
}
