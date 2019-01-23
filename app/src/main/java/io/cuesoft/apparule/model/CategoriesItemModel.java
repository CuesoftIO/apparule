package io.cuesoft.apparule.model;

public class CategoriesItemModel {
    private String categoriesText;
    private int imageResources;

    public CategoriesItemModel(String categoriesText, int imageResources) {
        this.categoriesText = categoriesText;
        this.imageResources = imageResources;
    }

    public String getCategoriesText() {
        return categoriesText;
    }

    public int getImageResources() {
        return imageResources;
    }
}
