package com.smartcontact.Repository;

import com.smartcontact.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByMobile(String mobile);

    User findByEmailAndPassword(String email, String password);

    User findById(int id);
}
