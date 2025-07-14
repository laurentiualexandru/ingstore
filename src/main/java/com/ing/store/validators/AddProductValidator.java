package com.ing.store.validators;

import com.ing.store.requests.ProductRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddProductValidator implements ConstraintValidator<ValidAddProduct, ProductRequest> {

    @Override
    public boolean isValid(ProductRequest product, ConstraintValidatorContext context) {
        if (product.name() == null || product.name().isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name is mandatory for add")
                    .addPropertyNode("name").addConstraintViolation();
            return false;
        }
        if (product.price() == null || product.price() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price must be >= 0 for add")
                    .addPropertyNode("price").addConstraintViolation();
            return false;
        }
        if (product.quantity() == null || product.quantity() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Quantity must be >= 0 for add")
                    .addPropertyNode("price").addConstraintViolation();
            return false;
        }
        return true;
    }
}
