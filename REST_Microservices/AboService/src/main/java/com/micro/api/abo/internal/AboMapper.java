package com.micro.api.abo.internal;

import com.micro.api.abo.Abo;

/**
 * Mapper to separate api objects and internal database objects.
 */
class AboMapper {

    /**
     * @param entity from the database
     * @return api model
     */
    public static Abo model(AboEntity entity) {
        Abo model = new Abo();
        model.setId(entity.getId());
        model.setProductNr(entity.getProductNr());
        model.setProductName(entity.getProductName());
        model.setPrice(entity.getPrice());
        model.setPayed(entity.getPayed());
        return model;
    }
}
