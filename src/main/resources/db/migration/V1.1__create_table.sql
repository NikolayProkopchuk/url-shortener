CREATE TABLE shortened_urls (
    id TEXT PRIMARY KEY,
    original_url TEXT NOT NULL UNIQUE,
    title TEXT NOT NULL,
    created_at TIMESTAMP default now()
);

CREATE UNIQUE INDEX shortened_urls_idx ON shortened_urls (original_url);
