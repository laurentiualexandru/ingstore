package com.ing.store.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PatchPriceProductValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPatchProduct {
    String message() default "Invalid product for Patch";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}