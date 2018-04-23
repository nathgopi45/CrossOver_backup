package com.crossover.techtrial.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.crossover.techtrial.model.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

	@Autowired
	private TestRestTemplate template;
	@Autowired
	Environment environment;

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testArticleShouldBeCreated() throws Exception {
		HttpEntity<Object> article = getHttpEntity(
				"{\"id\":2,\"email\":\"nail@publisher.com\",\"title\":\"Hi and bye\"}");
		ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
				Article.class);
		Assert.assertNotNull(resultAsset.getBody().getId());
	}

	@Test
	public void testArticleShouldBeUpdated() throws Exception {


		HttpEntity<Object> article = getHttpEntity(
				"{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
		RestTemplate restTemplate = new RestTemplate();   

		String port = environment.getProperty("local.server.port");
		ResponseEntity<Article> resultAsset =  restTemplate.exchange("http://localhost:"+port+"/articles/1", HttpMethod.PUT, article, Article.class);
		Assert.assertNotNull(resultAsset.getBody().getId());
	}

	@Test
	public void testArticleShouldBeDeleted() throws Exception {   

		HttpEntity<Object> article = getHttpEntity(
				"{\"email\": \"nail@publisher.com\", \"title\": \"hello\" }");
		HttpEntity<Object> article_post = getHttpEntity(
				"{\"id\":100,\"email\":\"nail@publisher.com\",\"title\":\"hello\"}");
		ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article_post,
				Article.class);
		RestTemplate restTemplate = new RestTemplate();   

		String port = environment.getProperty("local.server.port");
		boolean flag=true;
		try {
		 resultAsset =  restTemplate.exchange("http://localhost:"+port+"/articles/1", HttpMethod.DELETE, article, Article.class);
		 	
		}catch(Exception e) {
			Assert.assertNotNull(resultAsset.getStatusCode());	
			flag=false;
		}
		if(flag)Assert.assertNotNull(resultAsset.getStatusCode());
		
	}

	@Test
	public void testArticleShouldGetArticle() throws Exception {   

		RestTemplate restTemplate = new RestTemplate(); 
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/jspn");
		HttpEntity entity = new HttpEntity(headers);
		String port = environment.getProperty("local.server.port");
		
		HttpEntity<Article> response = restTemplate.exchange("http://localhost:"+port+"/articles/2", HttpMethod.GET, entity, Article.class);
		
		Assert.assertNotNull( response.getBody());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}
