INSERT INTO tl_prd_category (id, name) VALUES (1, 'shoes');
INSERT INTO tl_prd_category (id, name) VALUES (2, 'books');
INSERT INTO tl_prd_category (id, name) VALUES (3, 'electronics');

INSERT INTO tl_prd_product (id, name, description, stock,price,status, create_at,category_id)
VALUES (1000, 'adidas Cloudfoam Ultimate','Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from ADIDAS',5,178.89,'CREATED','2018-09-05',1);

INSERT INTO tl_prd_product (id, name, description, stock,price,status, create_at,category_id)
VALUES (1002, 'under armour Men ''s Micro G Assert – 7','under armour Men ''Lightweight mesh upper delivers complete breathability . Durable leather overlays for stability ',4,12.5,'CREATED','2018-09-05',1);


INSERT INTO tl_prd_product (id, name, description, stock,price,status, create_at,category_id)
VALUES (1003, 'Spring Boot in Action','under armour Men '' Craig Walls is a software developer at Pivotal and is the author of Spring in Action',12,40.06,'CREATED','2018-09-05',2);