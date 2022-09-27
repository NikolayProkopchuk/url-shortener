package com.prokopchuk.urlshortener.repository;

import java.util.Optional;

import com.prokopchuk.urlshortener.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {

    Optional<ShortenedUrl> findByOriginalUrl(String originalUrl);
}
