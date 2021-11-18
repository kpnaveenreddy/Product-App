package com.application.productapp.Repository;

import com.application.productapp.Entity.product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<product,Integer> {

    public product findByName(String name);
}
