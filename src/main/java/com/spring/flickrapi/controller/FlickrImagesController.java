package com.spring.flickrapi.controller;

import com.spring.flickrapi.entity.FlickrImagesEntity;
import com.spring.flickrapi.model.FlickrImagesResponseVO;
import com.spring.flickrapi.repository.FlickrImagesRepository;
import com.spring.flickrapi.service.FlickrImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api")
public class FlickrImagesController {

    @Autowired
    private FlickrImagesRepository flickrImagesRepository;

    @Autowired
    private FlickrImagesService flickrImagesService;

    @GetMapping("/getNewDefaultFeedData")
    public List<FlickrImagesResponseVO> getNewDefaultFeedData(){

        // Call Service to get the default new data from Flickr Feeds and save to local DB
        return flickrImagesService.getFlickrFeedPhotosDefault();

    }

    @GetMapping("/getAllFeedData")
    public List<FlickrImagesResponseVO> getAllFeedData(){

        // Call Service to get all existing data in local DB
        return flickrImagesService.getAllFeedData();

    }

    @GetMapping("/getNewFeedDataWithTags")
    public List<FlickrImagesResponseVO> getNewFeedDataWithTags(@RequestParam String tags){

        // Call Service to get new data from flickr with tags parameter and save it to local DB
        return flickrImagesService.getFlickrFeedPhotosWithTags(tags);

    }

    @GetMapping("/getFeedDataWithTags")
    public List<FlickrImagesResponseVO> getFeedDataWithTags(@RequestParam String tags){

        // Call Service to get the feed data from Local DB with tags
        return flickrImagesService.getFeedDataByTags(tags);

    }

    @DeleteMapping("/deleteAllFeed")
    public String deleteFeedData(){

        // Call Service to clear up existing data in local DB
        flickrImagesService.deleteAllFeed();

        return "Success";

    }

}
