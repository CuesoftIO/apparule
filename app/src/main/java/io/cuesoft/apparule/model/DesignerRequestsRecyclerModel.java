package io.cuesoft.apparule.model;

public class DesignerRequestsRecyclerModel {

    private String customerRequestName;
    private String itemRequestTitle;
    private String itemRequestTime;
    private int customerRequestImage;
    private int itemRequestImage;

    public DesignerRequestsRecyclerModel(String customerRequestName, String itemRequestTitle,
                                         String itemRequestTime, int customerRequestImage, int itemRequestImage) {
        this.customerRequestName = customerRequestName;
        this.itemRequestTitle = itemRequestTitle;
        this.itemRequestTime = itemRequestTime;
        this.customerRequestImage = customerRequestImage;
        this.itemRequestImage = itemRequestImage;
    }


    public String getCustomerRequestName() {
        return customerRequestName;
    }

    public String getItemRequestTitle() {
        return itemRequestTitle;
    }

    public String getItemRequestTime() {
        return itemRequestTime;
    }

    public int getCustomerRequestImage() {
        return customerRequestImage;
    }

    public int getItemRequestImage() {
        return itemRequestImage;
    }
}
