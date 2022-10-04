package com.prokopchuk.urlshortener;

import java.net.URI;

import javax.validation.Valid;

import com.prokopchuk.urlshortener.dto.ShortUrlDto;
import com.prokopchuk.urlshortener.entity.ShortenUrl;
import com.prokopchuk.urlshortener.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
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

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<Void> shortUrl(@RequestBody @Valid ShortUrlDto shortUrlDto) {
        ShortenUrl shortenUrl = shortUrlService.shortUrl(shortUrlDto);

        return ResponseEntity.status(HttpStatus.CREATED)
          .location(URI.create(shortenUrl.getId()))
          .build();
    }

    @GetMapping("/{shortenUrlId}")
    public ResponseEntity<Void> redirectByShortUrl(@PathVariable String shortenUrlId) {
        var shortenUrl = shortUrlService.getShortUrl(shortenUrlId);

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
          .location(URI.create(shortenUrl.getOriginalUrl()))
          .build();
    }
}
