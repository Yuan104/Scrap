package org.example.scrapebackendtest01.Service;

import org.example.scrapebackendtest01.Entity.Product;
import org.example.scrapebackendtest01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Product createUser(String name, String email) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(email);
        return userRepository.save(product);
    }
}
