package io.cuesoft.apparule.model;

public class DesignerCatalogueRecyclerModel{

    private String itemCatalogueText;
    private String descriptionCatalogue;
    private String timeCatalogue;
    private String priceCatalogue;
    private int imageCatalogue;


    public DesignerCatalogueRecyclerModel(String itemCatalogueText,
                                          String descriptionCatalogue, String timeCatalogue, String priceCatalogue, int imageCatalogue) {
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

    public int getImageCatalogue() {
        return imageCatalogue;
    }
}
