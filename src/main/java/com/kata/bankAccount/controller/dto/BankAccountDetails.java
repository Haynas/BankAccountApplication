package com.kata.bankAccount.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BankAccountDetails {
    List<RecordDto> recordDtos;
    Float balance;
    Long accountId;
}
