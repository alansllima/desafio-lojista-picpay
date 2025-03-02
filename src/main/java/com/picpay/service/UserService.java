package com.picpay.service;

import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.domain.user.User;
import com.picpay.domain.user.UserDTO;
import com.picpay.exception.EntityNotFoundException;
import com.picpay.exception.ResourceNotFoundException;
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
        Optional<User> byId= Optional.empty();
        try {
            User userSaved = userRepository.save(user);
            byId=  userRepository.findById(userSaved.getId());

             user = byId.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

             userDTO = new UserDTO(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getDocument(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getBalance(),
                    user.getUserType());

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
            Optional<User> userFind = userRepository.findById(id);
           userFind.orElseThrow(()-> new ResourceNotFoundException("User with id: "+id+" not found"));
            return userFind;
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
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
