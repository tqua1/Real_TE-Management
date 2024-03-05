package com.financialtracker.vault.controller;


import com.financialtracker.vault.dao.CategoryDao;
import com.financialtracker.vault.exception.ServiceException;
import com.financialtracker.vault.model.Category;
import com.financialtracker.vault.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


    @RestController
    @PreAuthorize("isAuthenticated")
    @CrossOrigin
    public class CategoryController {

        private final CategoryService categoryService;
        private final CategoryDao categoryDao;
        public CategoryController(CategoryService accountService, CategoryDao categoryDao) {
            this.categoryService = accountService;
            this.categoryDao = categoryDao;
        }

        @PreAuthorize("hasRole('ROLE_USER')")
        @GetMapping("/categories")
        public List<Category> getCategories(@Valid Principal principal) {
            try{
                List<Category> categories = categoryService.viewAllCategories(principal);
                return categories;
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }

        }

        @GetMapping("/categories/{categoryId}")
        public Category getCategoryById(@Valid Principal principal, @PathVariable("categoryId") int categoryId) {
            try {
                Category category = categoryService.viewCategoryById(principal, categoryId);
                if (category == null) {
                    throw new SecurityException("Category not found");
                }
                return category;
            }catch (SecurityException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }
        }


        @PostMapping("/categories")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Category> sendCategory(@Valid Principal principal, @RequestBody Category
                newCategory){
            Category createdCategory= null;
            try{
                createdCategory = categoryService.createCategory(principal,newCategory);
                if(createdCategory == null){
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    return ResponseEntity.ok(createdCategory);
                }
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("categories/update/{categoryId}")
        public Category updateCategory(@Valid Principal principal, @RequestBody Category category, @PathVariable("categoryId") int categoryId) {
            category.setCategoryId(categoryId);
            try{
                Category updateCategory = CategoryService.updateCategory(principal,category);
                if(updateCategory == null){
                    throw new ServiceException("Category not found ");
                }
                return updateCategory;
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }
        }

        @PreAuthorize("isAuthenticated")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/categories/{categoryId}")
        public void deleteCategory(@PathVariable int categoryId, Principal principal) {
            try{
                categoryService.deleteCategory(principal, categoryId );
            } catch (SecurityException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            }
    }
}
