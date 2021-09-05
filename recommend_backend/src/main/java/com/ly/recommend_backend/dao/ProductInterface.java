package com.ly.recommend_backend.dao;

import com.ly.recommend_backend.entity.ProductEntityP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInterface extends JpaRepository<ProductEntityP, Integer>, JpaSpecificationExecutor<ProductEntityP> {
    ProductEntityP getProductByProductId(Integer productid);
    @Query("select product from ProductEntityP product where product.name like CONCAT('%', :name, '%')")
    List<ProductEntityP> findByNameLike(@Param("name") String name);
}
