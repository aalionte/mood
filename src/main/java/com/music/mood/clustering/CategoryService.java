package com.music.mood.clustering;

import com.music.mood.model.Category;
import com.music.mood.vocabulary.model.NRCCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService {

    public Map<String, Category> getCategories(Map<String, NRCCategory> wordModels) {
        Map<String, Category> categoryArrayList = new HashMap<>();
        wordModels.forEach((s, nrcCategory) -> {
                    if (categoryArrayList.containsKey(nrcCategory.getCategory())) {
                        Category nrcCategoryItem = categoryArrayList.get(nrcCategory.getCategory());
                        nrcCategoryItem.addWord(nrcCategory.getWord());
                        categoryArrayList.remove(nrcCategory.getCategory());
                        categoryArrayList.put(nrcCategory.getCategory(), nrcCategoryItem);
                    } else {
                        categoryArrayList.put(nrcCategory.getCategory(), new Category(nrcCategory.getWord(), nrcCategory.getCategory()));
                    }
                }
        );
        return categoryArrayList;
    }
}
