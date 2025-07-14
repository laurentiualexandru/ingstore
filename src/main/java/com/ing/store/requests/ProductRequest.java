package com.ing.store.requests;

import com.ing.store.validators.*;

@ValidAddProduct(groups = AddGroup.class)
@ValidPatchProduct(groups = PatchGroup.class)
@ValidGetProduct(groups = GetGroup.class)
public record ProductRequest (String name,
                              Float price,
                              Integer quantity) {
}
