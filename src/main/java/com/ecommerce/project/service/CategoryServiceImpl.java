package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl  implements CategoryService{
    private List<Category> categories=new ArrayList<>();
    private  Long categoryId=1L;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(categoryId++);
         categories.add(category);
    }

    public String deleteCategory(Long categoryId){
        Category category=categories.stream().filter(c->c.getCategoryId().equals(categoryId))
                      .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        if(category == null)
        {
            return "Category not found";
        }
        categories.remove(category);
        return "ID "+categoryId+" has been removed";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {
       Optional<Category> optionalCategory= categories.stream().filter(c->c.getCategoryId().equals(categoryId))
               .findFirst();
       if(optionalCategory.isPresent()){
           Category existingCategory=optionalCategory.get();
           existingCategory.setCategoryName(category.getCategoryName());
           return existingCategory;
       }else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found");
       }

    }
}
