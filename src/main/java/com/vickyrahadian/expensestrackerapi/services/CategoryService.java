package com.vickyrahadian.expensestrackerapi.services;

import com.vickyrahadian.expensestrackerapi.domain.Category;
import com.vickyrahadian.expensestrackerapi.exceptions.EtBadRequestException;
import com.vickyrahadian.expensestrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String desc) throws EtBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
