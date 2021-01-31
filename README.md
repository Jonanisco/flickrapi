# flickrapi

## Intoduction
Here is a simple Spring Boot Application that provide an endpoint with photos data. The data collected from public Feed Flickr repository.The data stored into the DB by using H2Base Database since its just for test purpose.

## Highlated Dependecies Included 
  
    1. Spring boot starter data JPA
    2. H2 Base data base
    3. Lombok
    4. SwaggerUI
    5. Model mapper
    
## What Are The EndPoints Provided ?

    1. /public/api/getNewDefaultFeedData -> This end point will get new data from flickr feed and store it into local db and return the data
    2. /public/api/getNewFeedDataWithTags?tags=cats -> This end point will get new data with tags parameter from flickr and store it into local db and return the data
    3. /public/api/getAllFeedData -> This end point will get all feed data straight from the local storage
    4. /public/api/getFeedDataWithTags?tags=cats -> This end pont will get all feed data straight from the local storage with tags filtering
    5. /public/api/deleteAllFeed -> This end point will clear all existing data in local storage
    

## Demo
You can be test in my heroku site (https://flickrspringapi-heroku-demo.herokuapp.com)
    
       
    




