package com.example.Full_Project.Service;

import com.example.Full_Project.Model.Category;
import com.example.Full_Project.Model.Dto.CategoryOption;
import com.example.Full_Project.Repository.CategoryRepo;
import com.example.Full_Project.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepo categoryRepo;

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
}
