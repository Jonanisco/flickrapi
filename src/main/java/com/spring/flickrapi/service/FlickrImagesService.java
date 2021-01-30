package com.spring.flickrapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.flickrapi.entity.FlickrImagesEntity;
import com.spring.flickrapi.model.FlickrImagesResponseVO;
import com.spring.flickrapi.model.FlickrImagesVO;
import com.spring.flickrapi.repository.FlickrImagesRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlickrImagesService {

    @Autowired
    private FlickrImagesRepository flickrImagesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.spring.flickrapi.flickr-url}")
    private String flickrFeedUrl;

    @SneakyThrows
    public List<FlickrImagesResponseVO> getFlickrFeedPhotosDefault(){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flickrFeedUrl)
                .queryParam("format", "json");

        return getFeedFromFlickr(builder);

    }

    @SneakyThrows
    public List<FlickrImagesResponseVO> getFlickrFeedPhotosWithTags(String tagsName){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flickrFeedUrl)
                .queryParam("format", "json")
                .queryParam("tags", tagsName);

        return getFeedFromFlickr(builder);

    }

    public List<FlickrImagesResponseVO> getFeedDataByTags(String tags){
        ModelMapper modelMapper = new ModelMapper();
        return flickrImagesRepository.findAllByTagsContaining(tags)
                .orElse(new ArrayList<>())
                .stream().map(entity -> modelMapper.map(entity, FlickrImagesResponseVO.class))
                .collect(Collectors.toList());

    }

    @SneakyThrows
    private List<FlickrImagesResponseVO> getFeedFromFlickr(UriComponentsBuilder builder){


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers) ,
                String.class);

        String responseResult = response.getBody();

        responseResult = responseResult.substring(15, responseResult.length()-1);

        ObjectMapper objectMapper = new ObjectMapper();

        FlickrImagesVO flickrImagesVO = objectMapper.readValue(responseResult, FlickrImagesVO.class);


        ModelMapper modelMapper = new ModelMapper();
        List<FlickrImagesEntity> flickrImagesEntityList = flickrImagesRepository.saveAll(flickrImagesVO.getItems().stream()
                .map(imagesVO -> {
                    FlickrImagesEntity flickrImagesEntity = modelMapper.map(imagesVO, FlickrImagesEntity.class);
                    flickrImagesEntity.setImageUrl(imagesVO.getMedia().getImageUrl());
                    flickrImagesEntity.setDateTaken(imagesVO.getDateTaken().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    return flickrImagesEntity;
                })
                .collect(Collectors.toList()));

        return flickrImagesEntityList.stream().map(entity -> modelMapper.map(entity, FlickrImagesResponseVO.class)).collect(Collectors.toList());

    }

    public void deleteAllFeed(){

        flickrImagesRepository.deleteAll();

    }

    public List<FlickrImagesEntity> getAllFeedData(){

        return flickrImagesRepository.findAll();

    }

}
