package com.picpay.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record UserDTO( String firstName, String lastName, String document, String email, String password, BigDecimal balance,UserType userType) {

}
