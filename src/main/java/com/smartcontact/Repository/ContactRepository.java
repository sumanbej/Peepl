package com.smartcontact.Repository;

import com.smartcontact.Entity.Contact;
import com.smartcontact.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<User, Contact> {
}
