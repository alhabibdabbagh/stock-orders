-- ÖNCE customer ve asset tablolarına veri ekleyin
INSERT INTO stock_schema.customer (id, username, password, role)
VALUES (1, 'admin', 'admin123', 'ADMIN'),
       (2, 'ahmet', 'ahmet123', 'CUSTOMER'),
       (3, 'ayse', 'ayse123', 'CUSTOMER'),
       (4, 'mehmet', 'mehmet123', 'CUSTOMER'),
       (5, 'fatma', 'fatma123', 'CUSTOMER'),
       (6, 'mustafa', 'mustafa123', 'CUSTOMER'),
       (7, 'zeynep', 'zeynep123', 'CUSTOMER');

-- Sonra asset tablosu
INSERT INTO stock_schema.asset (id, asset_name, size, usable_size)
VALUES (1, 'TRY', 100000, 100000),
       (2, 'TRY', 50, 30),
       (3, 'TRY', 50000, 50000),
       (5, 'USD', 5000, 5000),
       (6, 'EUR', 3000, 2500),
       (7, 'GOLD', 100, 80),
       (8, 'BTC', 5, 3),
       (9, 'ETH', 10, 8);

-- EN SON orders tablosu (foreign key'lerin referans aldığı veriler hazır olduğunda)
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
