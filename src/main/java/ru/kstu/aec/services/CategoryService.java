package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Category;
import ru.kstu.aec.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new Exception("Такого теста нет, id = " + id));
    }

    public Category getCategoryByName(String name) throws Exception {
        Optional<Category> category = categoryRepository.findByName(name);
        return category.orElseThrow(() -> new Exception("Такого теста нет, name = " + name));
    }

    public List<Category> loadCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
