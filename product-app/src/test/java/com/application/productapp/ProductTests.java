package com.application.productapp;

import com.application.productapp.Entity.product;
import com.application.productapp.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class ProductTests {

    @Autowired
    private ProductRepository repo;

    @Test

    public void testCreateProduct(){
        product product = new product("iPhone 10",789);
        product savedProduct = repo.save(product);
        assertNotNull(savedProduct);
    }

    @Test
    public void testFindProductByNameExist(){
        String name = "iPhone 10";
        product product=repo.findByName(name);
      assertThat(product.getName()).isEqualTo(name);
    }

    @Test
    public void testFindProductByNameNotExist(){
        String name = "iPhone 11";
        product product=repo.findByName(name);
        assertNotNull(product);
    }

    @Test
    public  void testUpdateProduct(){
        String productName = "Kindle Reader";
        product product = new product(productName,199);
        product.setId(2);

        repo.save(product);

        product updateProduct = repo.findByName(productName);

        assertThat(updateProduct.getName()).isEqualTo(productName);
    }

    @Test
    public void testListProducts() {
        List<product> products=(List<product>) repo.findAll();

        for(product product:products) {
            System.out.println(product);
        }

        assertThat(products).size().isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteProduct() {
       Integer id=2;

       boolean isExistBeforeDelete  = repo.findById(id).isPresent();

       repo.deleteById(id);
        boolean notExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(isExistBeforeDelete);
        assertTrue(notExistAfterDelete);


    }

}
