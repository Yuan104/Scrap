package org.example.scrapebackendtest01.Controller;

import org.example.scrapebackendtest01.Entity.Product;
import org.example.scrapebackendtest01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Product createUser(@RequestParam String name, @RequestParam String email) {
        return userService.createUser(name, email);
    }

    // GET endpoint to test the controller
    @GetMapping("/test")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }
}
