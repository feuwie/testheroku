package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.Blind;
import com.simakov.diploma.Model.Cart;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BlindRepository extends JpaRepository<Blind, Long> {
    Blind findByUuid(String uuid);
}
