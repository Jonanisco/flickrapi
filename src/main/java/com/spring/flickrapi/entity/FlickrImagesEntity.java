package com.spring.flickrapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity (name = "flick_pub_feed")
@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
public class FlickrImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "image_url")
    private String imageUrl;

    @Column(name = "date_taken", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTaken;

    @Column (name = "author")
    private String author;

    @Column (name = "description", columnDefinition="CLOB")
    private String description;

    @Column (name = "tags", columnDefinition="CLOB")
    private String tags;




}
