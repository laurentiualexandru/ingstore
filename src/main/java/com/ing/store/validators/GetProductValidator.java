package com.ing.store.validators;


import com.ing.store.requests.ProductRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GetProductValidator implements ConstraintValidator<ValidGetProduct, ProductRequest> {

    @Override
    public boolean isValid(ProductRequest product, ConstraintValidatorContext context) {
        if (product.name() == null || product.name().isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name is mandatory for add")
                    .addPropertyNode("name").addConstraintViolation();
            return false;
        }
        return true;
    }
}
