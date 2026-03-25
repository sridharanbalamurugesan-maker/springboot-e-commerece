package com.example.Full_Project.Controller;

import com.example.Full_Project.Model.Category;
import com.example.Full_Project.Response.ApiResponse;
import com.example.Full_Project.Service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RestControllerAdvice
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @PostMapping("/create-category")
    public ApiResponse<?> createCategory(@RequestPart Category categoryDetail, @RequestPart MultipartFile imagefile){
        try {
            return categoryService.createCategory(categoryDetail, imagefile);
        }
        catch (IOException e)
        { throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-all-categories")
    public ApiResponse<?> getAllCategories(){
            return categoryService.getAllCategories();
    }
    @GetMapping("/get-category-options")
    public ApiResponse<?> getCategoryOptions(){
        return categoryService.getCategoryOptions();
    }
    @PutMapping("/category-edit/{id}")
    public ApiResponse<?> editCategory(@PathVariable Long id,@RequestPart Category categoryDetail,@RequestPart MultipartFile imagefile)throws IOException{
        categoryDetail.setId(id);
        return categoryService.editCategory(categoryDetail,imagefile);
    }
    @DeleteMapping("/delete-category/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
