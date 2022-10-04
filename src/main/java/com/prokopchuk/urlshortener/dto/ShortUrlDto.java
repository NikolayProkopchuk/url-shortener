package com.prokopchuk.urlshortener.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public record ShortUrlDto(@URL(protocol = "https") String url, @NotBlank String title) {
}
