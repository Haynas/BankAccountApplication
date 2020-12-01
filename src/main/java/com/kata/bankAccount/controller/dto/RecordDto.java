package com.kata.bankAccount.controller.dto;

import com.kata.bankAccount.entity.RecordType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RecordDto {
	private Long id;
	private Float amount;
	private RecordType type;
	private LocalDateTime date;
	private Long account;

}
