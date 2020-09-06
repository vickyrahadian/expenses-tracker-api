package com.vickyrahadian.expensestrackerapi.repositories;

import com.vickyrahadian.expensestrackerapi.domain.Category;
import com.vickyrahadian.expensestrackerapi.exceptions.EtBadRequestException;
import com.vickyrahadian.expensestrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String desc) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId);

}
