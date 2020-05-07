package com.simakov.diploma.Repository;

import com.simakov.diploma.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByPhoneAndUsertype(String phone, String usertype);

	User findByPhoneAndPasswordAndUsertype(String phone, String password, String usertype);

	void deleteAllByUuid(String uuid);

	void deleteByPhoneAndUsertype(String phone, String usertype);

	User findByUserid(int userid);

	User findByPhoneAndPasswordAndUsertypeAndBan(String phone, String password, String usertype, int ban);

	User findByUuid(String uuid);
}
