CREATE EXTENSION IF NOT EXISTS btree_gist;

ALTER TABLE price_offer
DROP CONSTRAINT IF EXISTS price_offer_no_overlap;

ALTER TABLE price_offer
ADD CONSTRAINT price_offer_no_overlap
EXCLUDE USING GIST (
    supplier_id WITH =,
    product_id WITH =,
    daterange(valid_from, valid_to, '[]') WITH &&
);


INSERT INTO supplier (supplier_id, supplier_name, supplier_type, tax_id_number) VALUES ('f5dc9d88-52e1-400a-9e3b-368f6f54f346','Supplier_1','International','8468853533');
INSERT INTO supplier (supplier_id, supplier_name, supplier_type, tax_id_number) VALUES ('fb5d328b-82a8-427a-bded-db9f02048aaf','Supplier_2','Local','3767796412');
INSERT INTO supplier (supplier_id, supplier_name, supplier_type, tax_id_number) VALUES ('144f6a64-4d8f-40b5-bc3e-438e7382837c','Supplier_3','International','8443531546');
INSERT INTO product (product_id, product_name, product_type) VALUES ('de3736a6-77d6-4e89-a58a-cee385fa01f4','Peach','TypeB');
INSERT INTO product (product_id, product_name, product_type) VALUES ('8b32e0ce-fbc5-44ee-8c2f-009345e4c83d','Cherry','TypeC');
INSERT INTO product (product_id, product_name, product_type) VALUES ('1727605e-9bc2-4e33-97cf-f13c1a31264a','Peach','TypeA');
INSERT INTO product (product_id, product_name, product_type) VALUES ('ca1eac39-ab19-4818-81d5-1e1e3b72bd84','Cherry','TypeB');
INSERT INTO product (product_id, product_name, product_type) VALUES ('b00af989-953b-40f9-8bd7-dfa81fcee864','Apple','TypeC');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('ae6249b5-0e8b-4c28-a6d6-d629e99fa3eb','f5dc9d88-52e1-400a-9e3b-368f6f54f346','de3736a6-77d6-4e89-a58a-cee385fa01f4',47.99,'2026-01-08','2026-02-16');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('7f3edc36-6a2d-49ba-9400-30403fa3451f','f5dc9d88-52e1-400a-9e3b-368f6f54f346','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d',31.61,'2026-01-06','2026-03-11');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('148e185b-caa7-4c44-81c7-89aa04ced4b4','f5dc9d88-52e1-400a-9e3b-368f6f54f346','1727605e-9bc2-4e33-97cf-f13c1a31264a',25.87,'2026-01-09','2026-03-25');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('0d656b1b-40fb-4c48-b6fb-b85865e1aadb','f5dc9d88-52e1-400a-9e3b-368f6f54f346','ca1eac39-ab19-4818-81d5-1e1e3b72bd84',20.12,'2026-01-07','2026-03-07');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('f29aa6ba-5d20-42df-aedb-8f43ab06af37','f5dc9d88-52e1-400a-9e3b-368f6f54f346','b00af989-953b-40f9-8bd7-dfa81fcee864',19.28,'2026-02-03','2026-03-26');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('7149b098-74c1-4878-a26a-527eddc3bcbd','fb5d328b-82a8-427a-bded-db9f02048aaf','de3736a6-77d6-4e89-a58a-cee385fa01f4',14.07,'2026-01-17','2026-04-04');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('43bc2361-56c2-4b87-80e9-c20ef1ab5cf8','fb5d328b-82a8-427a-bded-db9f02048aaf','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d',36.46,'2026-01-05','2026-03-30');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('5b0a2e05-af14-400c-9aa4-187cad8077df','fb5d328b-82a8-427a-bded-db9f02048aaf','1727605e-9bc2-4e33-97cf-f13c1a31264a',29.49,'2026-01-15','2026-02-14');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('4e242ee3-6716-4f4e-98ad-9e040924cf4f','fb5d328b-82a8-427a-bded-db9f02048aaf','ca1eac39-ab19-4818-81d5-1e1e3b72bd84',21.81,'2026-01-09','2026-02-09');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('7c9f33df-80da-42ae-8be5-d570b3f8ca44','fb5d328b-82a8-427a-bded-db9f02048aaf','b00af989-953b-40f9-8bd7-dfa81fcee864',24.73,'2026-01-25','2026-04-19');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('2a44296b-10d1-480f-b8fe-acbddb5cd517','144f6a64-4d8f-40b5-bc3e-438e7382837c','de3736a6-77d6-4e89-a58a-cee385fa01f4',24.78,'2026-01-25','2026-03-09');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('84e03365-9c3c-43b5-9800-357ec040791e','144f6a64-4d8f-40b5-bc3e-438e7382837c','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d',39.7,'2026-01-06','2026-02-05');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('21cfd442-4f59-4622-970b-420a9fc2b2e9','144f6a64-4d8f-40b5-bc3e-438e7382837c','1727605e-9bc2-4e33-97cf-f13c1a31264a',19.28,'2026-01-18','2026-03-02');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('10d74f5d-1a45-43f7-856a-695083573315','144f6a64-4d8f-40b5-bc3e-438e7382837c','ca1eac39-ab19-4818-81d5-1e1e3b72bd84',38.31,'2026-01-07','2026-02-10');
INSERT INTO price_offer (price_offer_id, supplier_id, product_id, valid_price_per_kg, valid_from, valid_to) VALUES ('01aead71-9ee5-47b9-b4df-3e3ab7393444','144f6a64-4d8f-40b5-bc3e-438e7382837c','b00af989-953b-40f9-8bd7-dfa81fcee864',37.7,'2026-01-05','2026-03-05');
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('d29ffed7-0e07-46c6-9d89-fc54055dc98e','144f6a64-4d8f-40b5-bc3e-438e7382837c','2026-01-24',460441);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('0d565200-9107-4449-b798-0159c854cf4c','f5dc9d88-52e1-400a-9e3b-368f6f54f346','2026-01-12',248671);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('5d6ac2a3-a2c3-4451-951f-48fb16cefc2c','144f6a64-4d8f-40b5-bc3e-438e7382837c','2026-01-19',936330);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('260a89a0-8ad4-4c60-9ab6-ac3d20e5236d','f5dc9d88-52e1-400a-9e3b-368f6f54f346','2026-02-03',984847);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('040c28df-b7ef-4e2b-aa09-df7d23eceee5','fb5d328b-82a8-427a-bded-db9f02048aaf','2026-01-06',631440);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('d42fde29-86a5-4d3e-b861-4e65d2d4c35a','fb5d328b-82a8-427a-bded-db9f02048aaf','2026-01-17',200562);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('9f8e3fb4-2a76-49f5-863b-fec93c9fe259','fb5d328b-82a8-427a-bded-db9f02048aaf','2026-02-01',620352);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('e137714d-696c-4489-b961-5336baae4ee7','f5dc9d88-52e1-400a-9e3b-368f6f54f346','2026-01-11',269717);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('b65d737d-bb25-408c-a019-368377addee2','f5dc9d88-52e1-400a-9e3b-368f6f54f346','2026-01-21',704220);
INSERT INTO delivery (delivery_id, supplier_id, delivery_date, delivery_hash) VALUES ('018e5f3c-b533-4f4f-a0fa-219a68dd07de','144f6a64-4d8f-40b5-bc3e-438e7382837c','2026-01-17',921884);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('db565f56-eadf-4daf-9fb6-d915873ca08a','ca1eac39-ab19-4818-81d5-1e1e3b72bd84','d29ffed7-0e07-46c6-9d89-fc54055dc98e',22.9,215.26);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('1c806b0b-30d5-4cf2-935d-7a37afdfae62','1727605e-9bc2-4e33-97cf-f13c1a31264a','d29ffed7-0e07-46c6-9d89-fc54055dc98e',44.09,179.65);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('23f72a3a-490d-43c5-924d-add47d22cae7','ca1eac39-ab19-4818-81d5-1e1e3b72bd84','d29ffed7-0e07-46c6-9d89-fc54055dc98e',41.17,290.74);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('34e28cc8-f44a-49d8-bd73-c42346555551','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d','0d565200-9107-4449-b798-0159c854cf4c',45.75,404.41);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('9e18cedf-1729-47a7-b936-50048eebf612','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d','0d565200-9107-4449-b798-0159c854cf4c',37.74,204.61);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('8a8cea33-b671-4415-a16f-99ac69d43791','b00af989-953b-40f9-8bd7-dfa81fcee864','5d6ac2a3-a2c3-4451-951f-48fb16cefc2c',34.99,428.52);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('dcfe577d-d826-4e68-9019-6746857bee9a','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d','5d6ac2a3-a2c3-4451-951f-48fb16cefc2c',35.7,463.23);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('381adb8a-f50c-4683-a764-101b9996201a','b00af989-953b-40f9-8bd7-dfa81fcee864','5d6ac2a3-a2c3-4451-951f-48fb16cefc2c',22.45,400.86);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('9e708346-1add-4a12-9e45-4cbc3eb2e120','1727605e-9bc2-4e33-97cf-f13c1a31264a','260a89a0-8ad4-4c60-9ab6-ac3d20e5236d',5.98,437.03);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('70bcab8b-24df-4bd2-8ae1-43d8b816bcc6','ca1eac39-ab19-4818-81d5-1e1e3b72bd84','260a89a0-8ad4-4c60-9ab6-ac3d20e5236d',27.46,324.72);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('55b09e70-2f87-4f7b-800c-93e6aa2cdc7a','1727605e-9bc2-4e33-97cf-f13c1a31264a','260a89a0-8ad4-4c60-9ab6-ac3d20e5236d',38.2,349.61);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('e229bc69-568e-4f55-ac6f-db064f8647c0','de3736a6-77d6-4e89-a58a-cee385fa01f4','040c28df-b7ef-4e2b-aa09-df7d23eceee5',24.44,461.55);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('06189f02-7ce6-4275-818b-4ac093d8694e','8b32e0ce-fbc5-44ee-8c2f-009345e4c83d','040c28df-b7ef-4e2b-aa09-df7d23eceee5',19.46,222.22);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('92f7bc10-0eb3-429e-90cb-5d7dfa86cadc','b00af989-953b-40f9-8bd7-dfa81fcee864','d42fde29-86a5-4d3e-b861-4e65d2d4c35a',17.96,60.2);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('b2f830a9-4a7a-4760-a4d0-7b24247de63c','ca1eac39-ab19-4818-81d5-1e1e3b72bd84','d42fde29-86a5-4d3e-b861-4e65d2d4c35a',14.97,116.15);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('e298d2f7-cf4e-4dee-9ba0-a4f6cfa92d90','1727605e-9bc2-4e33-97cf-f13c1a31264a','d42fde29-86a5-4d3e-b861-4e65d2d4c35a',7.93,386.96);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('dc6d3863-1e49-4892-a5fa-91c4d56bd526','b00af989-953b-40f9-8bd7-dfa81fcee864','9f8e3fb4-2a76-49f5-863b-fec93c9fe259',47.05,369.72);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('14cce65b-6d9b-41a2-8ef3-8a50ae0c5ee1','b00af989-953b-40f9-8bd7-dfa81fcee864','9f8e3fb4-2a76-49f5-863b-fec93c9fe259',21.16,80.02);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('8c269e7d-a408-4052-b0e2-87e7e3ee34ef','1727605e-9bc2-4e33-97cf-f13c1a31264a','e137714d-696c-4489-b961-5336baae4ee7',9.68,21.9);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('ac43cb92-11f2-42bf-87fd-ef796e1d7c83','b00af989-953b-40f9-8bd7-dfa81fcee864','e137714d-696c-4489-b961-5336baae4ee7',8.39,134.3);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('1abc4212-f563-4d1b-8fce-438b44310fc6','b00af989-953b-40f9-8bd7-dfa81fcee864','e137714d-696c-4489-b961-5336baae4ee7',23.97,476.8);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('4dcf6729-2eaa-4a30-b193-3909fa625be7','b00af989-953b-40f9-8bd7-dfa81fcee864','b65d737d-bb25-408c-a019-368377addee2',21.32,443.71);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('dea79509-33ec-4b41-8436-97126727deef','b00af989-953b-40f9-8bd7-dfa81fcee864','b65d737d-bb25-408c-a019-368377addee2',29.29,217.81);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('6ab24d1b-88d2-417f-a8c0-d943a6b0156c','de3736a6-77d6-4e89-a58a-cee385fa01f4','018e5f3c-b533-4f4f-a0fa-219a68dd07de',19.67,449.65);
INSERT INTO delivery_item (delivery_item_id, product_id, delivery_id, price_per_kg, weight) VALUES ('3c4097b5-d3fa-4e84-ba07-9d00eeeec4fa','b00af989-953b-40f9-8bd7-dfa81fcee864','018e5f3c-b533-4f4f-a0fa-219a68dd07de',45.09,319.91);

