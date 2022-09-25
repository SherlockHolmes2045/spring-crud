package com.sherlock2045.demo.service;

import com.sherlock2045.demo.model.User;
import com.sherlock2045.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private Validator validator;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById((int) id).get();
    }

    public User save(User user) {

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
        return userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById((int) id);
    }

    public User update(User userBody, int id) {

        Set<ConstraintViolation<User>> violations = validator.validate(userBody);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            User _user = user.get();
            _user.setName(userBody.getName());
            _user.setEmail(userBody.getEmail());
            _user.setAge(userBody.getAge());
            return save(_user);
        } else {
            return null;
        }
    }
}
