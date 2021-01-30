package com.spring.flickrapi.repository;


import com.spring.flickrapi.entity.FlickrImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlickrImagesRepository extends JpaRepository<FlickrImagesEntity, Long> {

    Optional <List<FlickrImagesEntity>> findAllByTagsContaining(String tags);

}
