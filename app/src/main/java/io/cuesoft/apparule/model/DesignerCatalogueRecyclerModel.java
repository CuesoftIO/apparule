package io.cuesoft.apparule.model;

import com.google.firebase.storage.StorageReference;

public class DesignerCatalogueRecyclerModel{

    private String itemCatalogueText;
    private String descriptionCatalogue;
    private String timeCatalogue;
    private String priceCatalogue;
    private StorageReference imageCatalogue;


    public DesignerCatalogueRecyclerModel(String itemCatalogueText,
                                          String descriptionCatalogue, String timeCatalogue, String priceCatalogue, StorageReference imageCatalogue) {
        this.itemCatalogueText = itemCatalogueText;
        this.descriptionCatalogue = descriptionCatalogue;
        this.timeCatalogue = timeCatalogue;
        this.priceCatalogue = priceCatalogue;
        this.imageCatalogue = imageCatalogue;
    }


    public String getItemCatalogueText() {
        return itemCatalogueText;
    }

    public String getDescriptionCatalogue() {
        return descriptionCatalogue;
    }

    public String getTimeCatalogue() {
        return timeCatalogue;
    }

    public String getPriceCatalogue() {
        return priceCatalogue;
    }

    public StorageReference getImageCatalogue() {
        return imageCatalogue;
    }
}
