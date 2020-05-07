package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.AdminOrder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
    void deleteByOrderId(int orderId);
    void deleteAllByUuid(String uuid);
    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE admin_order DROP adminorder_id", nativeQuery = true)
    // void dropId();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE admin_order AUTO_INCREMENT = 1", nativeQuery = true)
    // void autoIncrement();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE admin_order ADD adminorder_id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;", nativeQuery = true)
    // void addId();

    // List<AdminOrder> findByUserEmail(String email);
    AdminOrder findByOrderIdAndProductId(int orderId, int productId);
    List<AdminOrder> findByUuid(String uuid);
}
