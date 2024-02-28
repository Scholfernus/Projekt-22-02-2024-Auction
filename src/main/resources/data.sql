INSERT INTO category_model(name) VALUES("Moto");
INSERT INTO category_model(name) VALUES("Pets");
INSERT INTO category_model(name) VALUES("Health");
INSERT INTO category_model(name) VALUES("Tools");

INSERT INTO auction_model(name, initial_price, current_price, description, end_time, category_id) VALUES("Opla sprzedam", 1.0, 1.0, "opel", "2023-09-10T09:25:12", 1);
INSERT INTO auction_model(name, initial_price, current_price, description, end_time, category_id) VALUES("Sprzedam toyotę", 2.0, 2.0, "toyota", "2023-09-10T09:25:12", 1);


INSERT INTO seller_model(name) VALUES("Janusz");

INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,1);
INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,2);

