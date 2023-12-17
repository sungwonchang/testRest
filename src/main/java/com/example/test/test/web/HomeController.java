package com.example.test.test.web;

import java.nio.charset.Charset;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {
    @GetMapping
    public String home() {
        String uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
        .queryParam("param", "홍길동")
        .build()
        .encode(Charset.forName("EUC-KR"))
        .toUriString();

        System.out.println(uri);

        String uri2 = UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
        .queryParam("param", "홍길동")
        .build()
        .encode(Charset.forName("utf-8"))
        .toUriString();

        System.out.println(uri2);

        

        return "Test";
    }
}
