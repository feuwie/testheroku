package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.Cart;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    // List<Cart> findByUserEmail(String email);
    List<Cart> findByUuid(String uuid);

    // List<Cart> findAllByUserEmail(String email);
    List<Cart> findAllByUuid(String uuid);

    // Cart findByCartIdAndUserEmail(int cartId, String email);
    Cart findByCartIdAndUuid(int cartId, String uuid);

    // void deleteAllByUserEmail(String email);
    void deleteAllByUuid(String uuid);

    // void deleteByCartIdAndUserEmail(int cartId, String email);
    void deleteByCartIdAndUuid(int cartId, String uuid);

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE cart DROP cart_id", nativeQuery = true)
    // void dropId();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    // void autoIncrement();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE cart ADD cart_id int UNSIGNED NOT NULL
    // AUTO_INCREMENT PRIMARY KEY FIRST;", nativeQuery = true)
    // void addId();
}
