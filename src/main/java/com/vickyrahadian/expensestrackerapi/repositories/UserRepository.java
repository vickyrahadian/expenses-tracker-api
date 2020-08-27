package com.vickyrahadian.expensestrackerapi.repositories;

import com.vickyrahadian.expensestrackerapi.domain.User;
import com.vickyrahadian.expensestrackerapi.exception.EtAuthException;
import org.springframework.stereotype.Component;

public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
