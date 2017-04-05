package com.youndevice.rest.validator;

import com.youndevice.repository.UserRepository;
import com.youndevice.rest.validator.annotations.UniqueEmail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        log.debug("UniqueEmailValidator initialized");
    }

    @Override
    public boolean isValid(String emailId, ConstraintValidatorContext context) {
        log.debug("Validating whether email is unique: " + emailId);
        return (userRepository.countByEmailId(emailId) < 1);
    }
}
