package com.spring.flickrapi.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class FlickrImagesResponseVO {

    private Long id;
    private String title;
    private String imageUrl;
    private LocalDateTime dateTaken;
    private String author;
    private String description;
    private String tags;

}
