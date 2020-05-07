package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.PlaceOrder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<PlaceOrder, Long> {
    PlaceOrder findByOrderId(int order);
    void deleteAllByUuid(String uuid);
    void deleteByOrderId(int orderId);

    PlaceOrder findByUuid(String uuid);
    List<PlaceOrder> findAllByUuid(String uuid);
    // PlaceOrder deleteAllByUuid(String uuid);

    PlaceOrder findByUuidAndOrderId(String uuid, int orderid);
    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE place_order DROP order_id", nativeQuery = true)
    // void dropId();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE place_order AUTO_INCREMENT = 1", nativeQuery =
    // true)
    // void autoIncrement();

    // @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Query(value = "ALTER TABLE place_order ADD order_id int UNSIGNED NOT NULL
    // AUTO_INCREMENT PRIMARY KEY FIRST;", nativeQuery = true)
    // void addId();
}
