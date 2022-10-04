package com.prokopchuk.urlshortener.service;

import java.time.LocalDateTime;

import com.prokopchuk.urlshortener.ShortenUrlRepository;
import com.prokopchuk.urlshortener.dto.ShortUrlDto;
import com.prokopchuk.urlshortener.entity.ShortenUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortenUrlRepository shortenUrlRepository;

    public ShortenUrl shortUrl(ShortUrlDto shortUrlDto) {
        return shortenUrlRepository.findFirstByOriginalUrl(shortUrlDto.url())
          .orElseGet(() -> shortenUrlRepository.save(
            ShortenUrl.builder()
            .originalUrl(shortUrlDto.url())
            .title(shortUrlDto.title())
            .createdAt(LocalDateTime.now())
            .build())
          );
    }

    public ShortenUrl getShortUrl(String shortenUrlId) {
        return shortenUrlRepository.findById(shortenUrlId).orElseThrow();
    }
}
