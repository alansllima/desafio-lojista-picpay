package com.picpay.service;

import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.domain.user.User;
import com.picpay.domain.user.UserDTO;
import com.picpay.exception.EntityNotFoundException;
import com.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        User user = new User(userDTO.firstName(),userDTO.lastName(),userDTO.document(), userDTO.email(),userDTO.password(),userDTO.balance(),userDTO.userType());
        try {
            userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            System.out.println("message-------"+ exception.getMessage());
            throw new RuntimeException(exception.getCause());

        }

         return userDTO;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }

    }

    public void updateUser(User user) {

        try {
            userRepository.save(user);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }

    }
    public void updateUserWithTransaction(TransactionDTO transactionDTO) {
        Optional<User> senderUser = getUserById(transactionDTO.sender());
        Optional<User> receiverUser = getUserById(transactionDTO.receiver());

        if(senderUser.isPresent() && receiverUser.isPresent()) {
            BigDecimal senderBalanceWithTransfer = senderUser.get().getBalance().subtract(transactionDTO.transferValue());
            senderUser.get().setBalance(senderUser.get().getBalance().subtract(transactionDTO.transferValue()));

            BigDecimal receiverBalanceWithTransfer = receiverUser.get().getBalance().add(transactionDTO.transferValue());
            receiverUser.get().setBalance(receiverBalanceWithTransfer);
            updateUser(senderUser.get());
            updateUser(receiverUser.get());
        }

    }




}
