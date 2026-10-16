package com.snapshop.ecom.repository;

import com.snapshop.ecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

    @Query("Select c from Category c where c.name= :name and c.parentCategory.name = :parentCategory")
    public Category findByNameAndParentCategory(@Param("name") String name, @Param("parentCategory") String parentCategory);
}
