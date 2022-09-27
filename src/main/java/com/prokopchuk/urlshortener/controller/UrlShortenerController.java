package com.prokopchuk.urlshortener.controller;

import java.net.URI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokopchuk.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping
    @Cacheable("create_short_url_cache")
    public ResponseEntity<Void> shortUrl(@RequestBody ObjectNode body) {
        var shortenedUrl = urlShortenerService.shortUrl(body);
        return ResponseEntity.created(URI.create(shortenedUrl.getId())).build();
    }

    @GetMapping(path = "/{shortenUrlId}")
    @Cacheable("redirect_by_short_url_cache")
    public ResponseEntity<Void> redirectByShortUrl(@PathVariable String shortenUrlId) {
        var shortenedUrl = urlShortenerService.getShortenedUrlById(shortenUrlId);

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).
          location(URI.create(shortenedUrl.getOriginalUrl()))
          .build();
    }
}
