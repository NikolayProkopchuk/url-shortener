package com.prokopchuk.urlshortener.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokopchuk.urlshortener.entity.ShortenedUrl;
import com.prokopchuk.urlshortener.repository.ShortenedUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final ShortenedUrlRepository shortenedUrlRepository;

    @Transactional
    public ShortenedUrl shortUrl(ObjectNode objectNode) {
        var originalUrl = objectNode.get("url").asText();
        var title = objectNode.get("title").asText();
        var shortenedUrl = shortenedUrlRepository.findByOriginalUrl(originalUrl)
          .orElseGet(() -> {
              var newShortenedUrl = new ShortenedUrl();
              newShortenedUrl.setOriginalUrl(originalUrl);
              return newShortenedUrl;
          });
        shortenedUrl.setTitle(title);
        return shortenedUrlRepository.save(shortenedUrl);
    }

    @Transactional(readOnly = true)
    public ShortenedUrl getShortenedUrlById(String id) {
        return shortenedUrlRepository.findById(id).orElseThrow();
    }
}
