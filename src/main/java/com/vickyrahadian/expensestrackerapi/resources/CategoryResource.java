package com.vickyrahadian.expensestrackerapi.resources;

import com.vickyrahadian.expensestrackerapi.domain.Category;
import com.vickyrahadian.expensestrackerapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Category> category = categoryService.fetchAllCategories(userId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId) {
        Integer userId = (Integer) request.getAttribute("userId");
        Category category = categoryService.fetchCategoryById(userId, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId, @RequestBody Category category) {
        Integer userId = (Integer) request.getAttribute("userId");
        categoryService.updateCategory(userId, categoryId, category);
        Map<String, Boolean> maps = new HashMap<>();
        maps.put("success", true);
        return new ResponseEntity<>(maps, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> categoryMap) {
        Integer userId = (Integer) httpServletRequest.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.removeCategoryWithAllTransactions(userId, categoryId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
