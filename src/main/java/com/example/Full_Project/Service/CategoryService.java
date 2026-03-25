package com.example.Full_Project.Service;

import com.example.Full_Project.Model.Category;
import com.example.Full_Project.Model.Dto.CategoryOption;
import com.example.Full_Project.Repository.CategoryRepo;
import com.example.Full_Project.Repository.ProductRepo;
import com.example.Full_Project.Response.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepo categoryRepo;
    @Autowired
    public ProductRepo productRepo;

    public ApiResponse<?> createCategory(Category categoryDetail, MultipartFile imageName) throws IOException {
        categoryDetail.setImageName(imageName.getOriginalFilename());
        categoryDetail.setImageType(imageName.getContentType());
        categoryDetail.setImageData(imageName.getBytes());
        categoryRepo.save(categoryDetail);
        return new ApiResponse<>(true, "Successfully Created Category", null);
    }

    public ApiResponse<?> getAllCategories() {
        List<Category> data = categoryRepo.findAll();
        return new ApiResponse<>(true, "Success", data);
    }

    public ApiResponse<?> getCategoryOptions() {
        List<Category> data =categoryRepo.findAll();
        List<CategoryOption> options  = data.stream()
                .map(cat->new CategoryOption(cat.getId(), cat.getName()))
                .toList();
        return new ApiResponse<>(true,"Success",options);
    }

    public ApiResponse<?> editCategory(Category updateData, MultipartFile imageFile) throws IOException {
        Category existingCategory=categoryRepo.findById(updateData.getId())
                .orElseThrow(()->new RuntimeException("Category Not Found"));
        existingCategory.setName(updateData.getName());
        if(imageFile !=null && !imageFile.isEmpty()){
            existingCategory.setImageName(imageFile.getOriginalFilename());
            existingCategory.setImageType(imageFile.getContentType());
            existingCategory.setImageData(imageFile.getBytes());
        }
        categoryRepo.save(existingCategory);
        return new ApiResponse<>(true,"Successfully Category Added",null);
    }
@Transactional
    public ApiResponse<?> deleteCategory(Long id) {
         productRepo.deleteByCategoryId(id);
         categoryRepo.deleteById(id);
         return new ApiResponse<>(true,"Deleted",null);
    }
}
