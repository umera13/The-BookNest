package com.booknest.dao;

import java.util.List;

import com.booknest.model.Category;

public interface CategoryDAO {

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String categoryName);

    boolean categoryExists(String categoryName);

    List<Category> getAllCategories();

	List<Category> searchCategories(String keyword);

}