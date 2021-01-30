package com.spring.flickrapi.controller;

import com.spring.flickrapi.entity.FlickrImagesEntity;
import com.spring.flickrapi.repository.FlickrImagesRepository;
import com.spring.flickrapi.service.FlickrImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/public/api")
public class testcontroller {

    @Autowired
    private FlickrImagesRepository flickrImagesRepository;

    @Autowired
    private FlickrImagesService flickrImagesService;

    @GetMapping ("/test")
    public List<FlickrImagesEntity> hellworld(){

        //return  "Hellow World bro";

        FlickrImagesEntity test = FlickrImagesEntity.builder()
                .author("a")
                .build();
        flickrImagesRepository.save(test);

        flickrImagesService.getFlickrFeedPhotosDefault();

        return flickrImagesRepository.findAll();




    }







}
