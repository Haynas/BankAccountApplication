package com.kata.bankAccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	List<Record> findByAccount(Account account);
}
