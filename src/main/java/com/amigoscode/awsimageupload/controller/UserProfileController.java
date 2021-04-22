package com.amigoscode.awsimageupload.controller;


import com.amigoscode.awsimageupload.profile.UserProfile;
import com.amigoscode.awsimageupload.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
//allows us to make a connection from everywhere. shouldn't do in production but because we are testing things locally it's fine
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }


    //allows us to assign an image to a profile Id
    @PostMapping(
            value = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file")MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    @GetMapping(value = "{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId) {
       return userProfileService.downloadUserProfileImage(userProfileId);
    }

}
