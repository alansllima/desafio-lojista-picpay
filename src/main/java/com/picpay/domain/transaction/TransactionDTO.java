package com.picpay.domain.transaction;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal transferValue, Long sender,Long receiver) {
}
