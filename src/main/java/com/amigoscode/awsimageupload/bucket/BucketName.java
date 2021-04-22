package com.amigoscode.awsimageupload.bucket;

public enum BucketName {

    //profile_image is our bucket-name
    //this links to amazon bucket
    PROFILE_IMAGE("image-upload123");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
