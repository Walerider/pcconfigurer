package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
