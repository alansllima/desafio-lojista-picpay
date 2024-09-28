package com.picpay.domain.transaction;

import com.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

import java.math.BigDecimal;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;
    private BigDecimal transferValue;

    private Long sender;
    private Long receiver;

    public Transaction(BigDecimal transferValue, Long sender, Long receiver) {
        this.transferValue = transferValue;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Transaction(){}

}
