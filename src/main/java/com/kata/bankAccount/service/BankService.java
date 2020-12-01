package com.kata.bankAccount.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.kata.bankAccount.controller.dto.RecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Customer;
import com.kata.bankAccount.entity.Record;
import com.kata.bankAccount.entity.RecordType;
import com.kata.bankAccount.exception.BusinessException;
import com.kata.bankAccount.exception.ExceptionType;
import com.kata.bankAccount.repository.AccountRepository;
import com.kata.bankAccount.repository.CustomerRepository;
import com.kata.bankAccount.repository.RecordRepository;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final RecordRepository recordRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BankService(AccountRepository accountRepository, RecordRepository recordRepository,
                       CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.recordRepository = recordRepository;
        this.customerRepository = customerRepository;
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Account getAccount(Long id) throws BusinessException {
        return accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.ACCOUNT_NOT_FOUND));
    }

    public Record addRecordToAccount(long accountId, RecordDto recordDto) throws BusinessException {
        Account account = getAccount(accountId);
        Record recordToAdd = Record.builder()
                .account(account)
                .date(LocalDateTime.now())
                .amount(recordDto.getAmount())
                .type(recordDto.getType())
                .build();
        account.setBalance(calculateBalance(account.getBalance(), recordToAdd));
        accountRepository.save(account);
        return recordRepository.save(recordToAdd);
    }

    public float calculateBalance(float balance, Record record) throws BusinessException {
        if (RecordType.DEPOSIT.equals(record.getType())) {
            balance += record.getAmount();
        } else {
            if (balance == 0 || balance < record.getAmount()) {
                throw new BusinessException(ExceptionType.OPERATION_NOT_ALLOWED);
            }
            balance -= record.getAmount();
        }
        return balance;
    }

    public List<Record> getRecordsByAccount(Account account) {
        return recordRepository.findByAccount(account);
    }

}
