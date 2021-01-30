package com.spring.flickrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlickrImagesVO {

    String title, link;
    Date modified;
    List<FlickrImagesItemVO> items;

}
