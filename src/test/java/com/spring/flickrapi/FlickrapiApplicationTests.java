package com.spring.flickrapi;

import com.spring.flickrapi.controller.FlickrImagesController;
import com.spring.flickrapi.model.FlickrImagesResponseVO;
import com.spring.flickrapi.service.FlickrImagesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FlickrapiApplicationTests {

	@Autowired
	FlickrImagesService service;

	@MockBean
	RestTemplate restTemplate;

	@Autowired
	ResourceLoader resourceLoader;

	@Test
	void GivenValidResponseFromFLickr_WhenGetFlickrFeedPhotosDefault_ExpectSuccess() {

		String data = null;


		try {
			Resource resource = resourceLoader.getResource("classpath:mockResponse.txt");

			InputStream input = resource.getInputStream();

			File file = resource.getFile();

			byte[] bdata = FileCopyUtils.copyToByteArray(input);
			data = new String(bdata, StandardCharsets.UTF_8);


		}
		catch (IOException e) {
			data = null;
			System.out.println("error");
		}

		ResponseEntity<String> mockedResponse = new ResponseEntity<String>(data,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<String>>any()))
				.thenReturn(mockedResponse);

		List<FlickrImagesResponseVO> result = service.getFlickrFeedPhotosDefault();
		assertThat(result.size()).isEqualTo(20);
		//assertThat(result.get(0).getTags().contains("cat")).isEqualTo(true);

	}

	@Test
	void GivenValidResponseFromFLickr_WhenGetFlickrFeedPhotosFilterByTags_ExpectSuccess() {

		String data = null;


		try {
			Resource resource = resourceLoader.getResource("classpath:mockResponseCats.txt");

			InputStream input = resource.getInputStream();

			File file = resource.getFile();

			byte[] bdata = FileCopyUtils.copyToByteArray(input);
			data = new String(bdata, StandardCharsets.UTF_8);


		}
		catch (IOException e) {
			data = null;
			System.out.println("error");
		}

		ResponseEntity<String> mockedResponse = new ResponseEntity<String>(data,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<String>>any()))
				.thenReturn(mockedResponse);

		List<FlickrImagesResponseVO> result = service.getAllFeedData();

		// Check the first row only to make sure it return cats in the tag
		assertThat(result.get(0).getTags().contains("cat")).isEqualTo(true);


	}


	@Test
	void GivenValidResponseFromLocalStorageWillAllData_ExpectSuccess() {

		List<FlickrImagesResponseVO> result = service.getAllFeedData();

		assertThat(result.size()).isEqualTo(40);

	}

	@Test
	void GivenValidResponseFromLocalStorageWillAllData_ExpectEmpty() {

		service.deleteAllFeed();

		List<FlickrImagesResponseVO> result = service.getAllFeedData();

		assertThat(result.size()).isEqualTo(0);

	}

	@Test
	void GivenValidResponseFromLocalStorageWithTags_ExpectSuccess() {

		List<FlickrImagesResponseVO> result = service.getFeedDataByTags("cats");

		// Check the first row only to make sure it return cats in the tag
		assertThat(result.get(0).getTags().contains("cat")).isEqualTo(true);

	}

}
