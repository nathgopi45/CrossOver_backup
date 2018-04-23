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
import com.crossover.techtrial.model.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

	@Autowired
	private TestRestTemplate template;
	@Autowired
	Environment environment;

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testCommentShouldBeCreated() throws Exception {
		HttpEntity<Object> article = getHttpEntity(
				"{\"id\":2,\"email\":\"nail@publisher.com\",\"message\":\"nice article\"}");
		ResponseEntity<Comment> resultAsset = template.postForEntity("articles/2/comments", article,
				Comment.class);
		Assert.assertNotNull(resultAsset.getBody().getId());
	}
	
	
	@Test
	public void testArticleShouldGetComment() throws Exception {   

		RestTemplate restTemplate = new RestTemplate(); 
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/jspn");
		HttpEntity entity = new HttpEntity(headers);
		String port = environment.getProperty("local.server.port");
		
		HttpEntity<Comment> response = restTemplate.exchange("http://localhost:"+port+"/articles/2/comments", HttpMethod.GET, entity, Comment.class);
		
		Assert.assertNotNull( response.getBody());
	}
	
	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}

