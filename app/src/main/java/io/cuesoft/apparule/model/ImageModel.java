package io.cuesoft.apparule.model;

import com.google.firebase.storage.StorageReference;

public class ImageModel {
    private StorageReference imageUrl;

    public ImageModel(StorageReference imageUrl) {
        this.imageUrl = imageUrl;
    }

    public StorageReference getImage() {
        return imageUrl;
    }
}
