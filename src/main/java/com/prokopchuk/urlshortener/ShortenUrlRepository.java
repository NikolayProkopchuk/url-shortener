package com.prokopchuk.urlshortener;

import java.util.Optional;

import com.prokopchuk.urlshortener.entity.ShortenUrl;
import org.springframework.data.repository.CrudRepository;

public interface ShortenUrlRepository extends CrudRepository<ShortenUrl, String> {

    Optional<ShortenUrl> findFirstByOriginalUrl(String originalUrl);
}
