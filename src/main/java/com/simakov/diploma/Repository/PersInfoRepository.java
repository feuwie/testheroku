package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.Cart;
import com.simakov.diploma.Model.PersInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersInfoRepository extends JpaRepository<PersInfo, Long> {
    PersInfo findByUuid(String uuid);

    void deleteAllByUuid(String uuid);

    void deleteByOrderId(int orderId);
}
