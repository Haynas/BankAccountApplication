package com.kata.bankAccount.service;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Customer;
import com.kata.bankAccount.entity.Record;
import com.kata.bankAccount.exception.BusinessException;

import java.util.List;

public interface BankService {

    void addAccount(Account account);

    void addCustomer(Customer customer);

    Account getAccount(Long id) throws BusinessException;

    Record addRecordToAccount(long accountId, RecordDto recordDto) throws BusinessException;

    List<Record> getRecordsByAccount(Account account);


}
