package com.psp.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psp.app.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
