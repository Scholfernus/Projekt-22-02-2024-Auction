DROP TABLE IF EXISTS seller_auction; -- Najpierw usuń ograniczenie klucza obcego

DROP TABLE IF EXISTS auction_model;

CREATE TABLE IF NOT EXISTS category_model (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS auction_model (
                                             id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
                                             seller_id BIGINT,
                                             name VARCHAR(255) NOT NULL,
                                             initial_price FLOAT(53) NOT NULL, -- Zmieniono typ kolumny na FLOAT
                                             current_price DOUBLE(8, 2),
                                             description VARCHAR(5000),
                                             end_time DATETIME NOT NULL,
                                             category_id BIGINT NOT NULL,
                                             FOREIGN KEY (category_id) REFERENCES category_model(id)
);

DROP TABLE IF EXISTS seller_model;

CREATE TABLE IF NOT EXISTS seller_model (
                                            seller_id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
                                            name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS seller_auction (
                                              seller_id BIGINT,
                                              auction_id BIGINT,
                                              FOREIGN KEY (seller_id) REFERENCES seller_model(seller_id),
                                              FOREIGN KEY (auction_id) REFERENCES auction_model(id)
);
