package com.prokopchuk.urlshortener.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "shortened_urls")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ShortenedUrl {

    @Id
    @GeneratedValue(generator = "short_url_id_generator")
    @GenericGenerator(name = "short_url_id_generator", strategy = "com.prokopchuk.urlshortener.ShortUrlIdGenerator")
    private String id;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        ShortenedUrl that = (ShortenedUrl) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
