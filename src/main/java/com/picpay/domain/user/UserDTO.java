package com.picpay.domain.user;


import java.math.BigDecimal;
public record UserDTO( Long id, String firstName, String lastName, String document, String email, String password, BigDecimal balance,UserType userType) {

}
