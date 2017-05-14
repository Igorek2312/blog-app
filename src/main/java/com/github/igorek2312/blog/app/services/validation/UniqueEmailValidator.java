package com.github.igorek2312.blog.app.services.validation;

import com.github.igorek2312.blog.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Rybak
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
   @Autowired
   private UserRepository userRepository;

   public void initialize(UniqueEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      return !userRepository.existsByEmail(email);
   }
}
