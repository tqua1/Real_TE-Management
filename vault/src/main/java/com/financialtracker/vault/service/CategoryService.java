package com.financialtracker.vault.service;

import com.financialtracker.vault.model.Category;

import java.security.Principal;
import java.util.List;

public interface CategoryService {
    Category updateCategory(Principal principal, Category category);

    List<Category> viewAllCategories(Principal principal);

    Category viewCategoryById(Principal principal, int categoryId);

    Category createCategory(Principal principal, Category newCategory);

    void deleteCategory(Principal principal, int categoryId);
}
