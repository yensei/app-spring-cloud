INSERT INTO tl_sho_invoice (id, number, description, customer_id, create_at, status) VALUES(1, '0001', 'invoice office items', 1, NOW(),'CREATED');

INSERT INTO tl_sho_inv_item ( invoice_id, product_id, quantity, price ) VALUES(1, 1000 , 1, 178.89);
INSERT INTO tl_sho_inv_item ( invoice_id, product_id, quantity, price)  VALUES(1, 1002 , 2, 12.5);
INSERT INTO tl_sho_inv_item ( invoice_id, product_id, quantity, price)  VALUES(1, 1003 , 1, 40.06);
