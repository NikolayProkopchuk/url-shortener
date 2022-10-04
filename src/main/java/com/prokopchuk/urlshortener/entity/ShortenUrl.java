package com.prokopchuk.urlshortener.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.validation.annotation.Validated;

@RedisHash
@Data
@Builder
@EqualsAndHashCode(of = "id")
@Validated
public class ShortenUrl {
    @Id
    private String id;

    @Indexed
    @URL(protocol = "https")
    private String originalUrl;

    @NotBlank
    private String title;

    private LocalDateTime createdAt;
}
