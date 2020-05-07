package com.simakov.diploma.Repository;

import java.util.List;

import com.simakov.diploma.Model.Promo;
import com.simakov.diploma.Model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductId(int prodId);
    void deleteAllByUserId(int userid);
    void deleteAllByProductId(int productId);
}
