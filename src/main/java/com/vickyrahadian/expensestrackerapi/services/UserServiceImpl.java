package com.vickyrahadian.expensestrackerapi.services;

import com.vickyrahadian.expensestrackerapi.domain.User;
import com.vickyrahadian.expensestrackerapi.exceptions.EtAuthException;
import com.vickyrahadian.expensestrackerapi.filter.AuthFilter;
import com.vickyrahadian.expensestrackerapi.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Log log = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        log.info(">> validateUser");
        if (email != null)
            email = email.toLowerCase();

        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        log.info(">> registerUser");
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (email != null)
            email = email.toLowerCase();

        if (!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");

        if (userRepository.getCountByEmail(email) > 0)
            throw new EtAuthException("Email already in use");

        return userRepository.findById(userRepository.create(firstName, lastName, email, password));
    }
}
