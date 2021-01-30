package com.spring.flickrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlickrImagesItemVO {

    String title, description, author, tags;

    @JsonProperty ("date_taken")
    Date dateTaken;

    FlickrImagesMediaVO media;


}
