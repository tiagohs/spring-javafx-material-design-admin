package com.tiagohs.util;

import javax.validation.Payload;
import javax.validation.Constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidatorImpl.class)
@Documented
public @interface PasswordValidator {
	String message() default "Senhas não batem";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
