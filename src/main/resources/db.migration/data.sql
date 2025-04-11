-- Customers
INSERT INTO stock_schema.customer (id, username, password, role)
VALUES (1, 'admin', 'admin123', 'ADMIN'),
       (2, 'ahmet', 'ahmet123', 'CUSTOMER'),
       (3, 'ayse', 'ayse123', 'CUSTOMER');

-- Assets
INSERT INTO stock_schema.asset (id, asset_name, size, usable_size)
VALUES (1, 'TRY', 100000, 100000),
       (2, 'TRY', 50, 30),
       (3, 'TRY', 50000, 50000),
       (4, 'TRY', 20, 20);

-- Orders
INSERT INTO stock_schema.orders (id, size, price, order_side, status, create_date, customer_id, asset_id)
VALUES (1, 10, 1500.00, 'BUY', 'PENDING', '2025-04-10T10:30:00', 2, 2),
       (2, 5, 1200.00, 'SELL', 'MATCHED', '2025-04-09T15:00:00', 2, 1),
       (3, 5, 1200.00, 'SELL', 'CANCELED', '2025-04-09T15:00:00', 2, 1),
       (4, 5, 1200.00, 'SELL', 'CANCELED', '2025-04-09T15:00:00', 2, 1),
       (5, 5, 1200.00, 'SELL', 'PENDING', '2025-04-09T15:00:00', 2, 1),
       (6, 5, 1200.00, 'SELL', 'PENDING', '2025-04-09T15:00:00', 2, 1),
       (7, 5, 1200.00, 'SELL', 'MATCHED', '2025-04-09T15:00:00', 2, 1),
       (8, 2, 1000.00, 'BUY', 'MATCHED', '2025-04-08T09:45:00', 3, 3);
