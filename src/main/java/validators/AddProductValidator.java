package validators;

import com.ing.store.dto.ProductDto;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddProductValidator implements Validator {
    @Override
    public boolean supports(@NonNull  Class<?> clazz) {
        return ProductDto.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull  Errors errors) {
        ProductDto productDto = (ProductDto) target;
        if (productDto.name() == null || productDto.name().isBlank()) {
            errors.rejectValue("name", "name.empty", "Name is required");
        }
        if (productDto.price() < 0) {
            errors.rejectValue("price", "price.invalid", "Price must be greater or equal to 0");
        }
        if (productDto.quantity() < 0) {
            errors.rejectValue("quantity", "quantity.invalid", "Quantity must be greater or equal to 0");
        }
    }
}
