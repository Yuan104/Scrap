package org.example.scrapebackendtest01.Repository;
import org.example.scrapebackendtest01.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Product, Long> {
}
