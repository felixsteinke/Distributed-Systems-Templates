package com.mono.api.product.internal;

import com.mono.api.product.Product;

/**
 * Mapper to separate api objects and internal database objects.
 */
class ProductMapper {

    /**
     * @param entity from the database
     * @return api model
     */
    public static Product model(ProductEntity entity) {
        Product model = new Product();
        model.setNr(entity.getNr());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        return model;
    }

    /**
     * @param model api model
     * @return entity from the database
     */
    public static ProductEntity entity(Product model) {
        ProductEntity entity = new ProductEntity();
        entity.setNr(model.getNr());
        entity.setName(model.getName());
        entity.setPrice(model.getPrice());
        return entity;
    }
}
