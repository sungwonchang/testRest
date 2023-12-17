package com.example.test.test;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.test.test.domain.FindModel;
import com.example.test.test.domain.Person;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	ApplicationRunner init(Home home) {
		return args -> {
			var name = URLEncoder.encode("홍길동", "EUC-KR");
			//http://localhost:8080?param=%C8%AB%B1%E6%B5%BF
			//http://localhost:8080?param=%ED%99%8D%EA%B8%B8%EB%8F%99


			// Create a RestClient instance
			//RestClient restClient = RestClient.create("https://search.paju.go.kr/ksf");
			//RestClient restClient = RestClient.builder().baseUrl("http://localhost:13418").build();

			// var restClient = RestClient.builder()
			// .defaultHeaders(headers -> {
			// 	headers.setContentType(new MediaType("application", "json", Charset.forName("EUC-KR")));
			// })
			// .messageConverters(configuration -> {
			// 	configuration.add(new StringHttpMessageConverter(Charset.forName("EUC-KR")));
			// })
			// .baseUrl("https://search.paju.go.kr/ksf")
			// .build();


			// Make a Post request
			// var response = restClient.post()
			// .uri("/Home/PostTest?name={name}", name)
			// .retrieve()
			// .body(String.class);

			// // Decode the response
			
			// // Process the response
			// System.out.println(response);
		
			System.out.println(name);

			var model = FindModel.builder().name("홍길동").build();
			var result = home.postTest(model);
			//var result = home.get();
			
			
			System.out.println(result.length);;


		};
	}

	interface Home {
		@PostExchange(url = "/Home/PostTest")
		Person[] postTest(@RequestBody FindModel model);
		
		@GetExchange(url = "/Home")
		Person[] get();
	}

	@Bean
	Home home() {
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:13418").build();
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(Home.class);
	}
}
