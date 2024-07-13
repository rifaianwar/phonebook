package com.r3s.phonebook.repository;

import com.r3s.phonebook.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    ContactEntity getById(Long id);
    List<ContactEntity> getAllByPhoneNumber(String phone);
    List<ContactEntity> getAllByGrup(String group);
}
