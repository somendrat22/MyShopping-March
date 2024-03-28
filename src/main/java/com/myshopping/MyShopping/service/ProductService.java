package com.myshopping.MyShopping.service;

import com.myshopping.MyShopping.exceptions.ResourceNotFound;
import com.myshopping.MyShopping.exceptions.UnAuthorized;
import com.myshopping.MyShopping.models.AppUser;
import com.myshopping.MyShopping.models.Product;
import com.myshopping.MyShopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    public void registerProduct(Product product, UUID sellerId){
        // we need to check this userId exists in our system or not
        // if userId exists what kind of userId it is
        // if it is seller kind of id then it is fine
        // else we need to throw exception
        // With the help of seller id we can get userObject or seller object
        // 1. Get user object by Id;
        AppUser user = userService.getUserById(sellerId);
        if(user == null){
            // user does not exist in our application
            throw new ResourceNotFound(String.format("Seller with sellerId %s does not exist in system.", sellerId.toString()));
        }

        if(!user.getUserType().equals("SELLER")){
            // If user is not of type SELLER -> We are going to throw unAuthorized exception
            throw new UnAuthorized(String.format("User with id %s does not have access to perform this operation", sellerId.toString()));
        }

        product.setUser(user);
        productRepository.save(product);
    }
}
