INSERT INTO tl_cus_region (id, name) VALUES (1, 'Sudamérica');
INSERT INTO tl_cus_region (id, name) VALUES (2, 'Centroamérica');
INSERT INTO tl_cus_region (id, name) VALUES (3, 'Norteamérica');
INSERT INTO tl_cus_region (id, name) VALUES (4, 'Europa');
INSERT INTO tl_cus_region (id, name) VALUES (5, 'Asia');
INSERT INTO tl_cus_region (id, name) VALUES (6, 'Africa');
INSERT INTO tl_cus_region (id, name) VALUES (7, 'Oceanía');
INSERT INTO tl_cus_region (id, name) VALUES (8, 'Antártida');

INSERT INTO tl_cus_customer (id,document_id,ruc,first_name,last_name , email, photo_url, region_id, status) 
    VALUES(1,'32404580','80045123-5', 'Juan', 'Peres', 'juanperes@email.com','http://localhost/photo/1',1,'CREATED');
