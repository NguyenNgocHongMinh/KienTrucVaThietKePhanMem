-- ==========================================
-- 1. TẠO DATABASE VÀ SỬ DỤNG DATABASE ĐÓ
-- ==========================================
CREATE DATABASE IF NOT EXISTS order_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE order_db;

-- ==========================================
-- 2. TẠO BẢNG (TABLES)
-- ==========================================
-- Bảng orders
CREATE TABLE IF NOT EXISTS orders (
                                      id VARCHAR(36) PRIMARY KEY, -- Sử dụng VARCHAR(36) để lưu UUID
    user_id VARCHAR(36) NOT NULL,
    total_amount DOUBLE NOT NULL,
    payment_method VARCHAR(50),
    status VARCHAR(50)
    );

-- Bảng order_items
CREATE TABLE IF NOT EXISTS order_items (
                                           id VARCHAR(36) PRIMARY KEY, -- Sử dụng VARCHAR(36) để lưu UUID
    order_id VARCHAR(36) NOT NULL,
    food_id VARCHAR(36) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    -- Tạo khóa ngoại liên kết với bảng orders
    CONSTRAINT fk_order_items_order
    FOREIGN KEY (order_id)
    REFERENCES orders(id)
    ON DELETE CASCADE
    );

-- ==========================================
-- 3. CHÈN DỮ LIỆU MẪU (DUMMY DATA)
-- ==========================================
-- Xóa dữ liệu cũ nếu chạy lại script nhiều lần (Xóa bảng con trước, bảng cha sau)
DELETE FROM order_items;
DELETE FROM orders;

-- Insert dữ liệu mẫu vào bảng orders (Sử dụng UUID cố định để bạn dễ test API)
INSERT INTO orders (id, user_id, total_amount, payment_method, status)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'user-uuid-001', 150000, 'COD', 'SUCCESS'),
    ('987fcdeb-51a2-43d7-9012-345678901234', 'user-uuid-002', 300000, 'BANKING', 'PENDING'),
    ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'user-uuid-001', 50000, 'COD', 'FAILED');

-- Insert dữ liệu mẫu vào bảng order_items
-- Dùng hàm UUID() của MariaDB để tự động sinh ID cho các item
INSERT INTO order_items (id, order_id, food_id, quantity, price)
VALUES
-- Chi tiết cho Đơn hàng 1 (ID: 123e4567-e89b-12d3-a456-426614174000)
(UUID(), '123e4567-e89b-12d3-a456-426614174000', 'food-uuid-101', 2, 50000),
(UUID(), '123e4567-e89b-12d3-a456-426614174000', 'food-uuid-102', 1, 50000),

-- Chi tiết cho Đơn hàng 2 (ID: 987fcdeb-51a2-43d7-9012-345678901234)
(UUID(), '987fcdeb-51a2-43d7-9012-345678901234', 'food-uuid-103', 3, 100000),

-- Chi tiết cho Đơn hàng 3 (ID: a1b2c3d4-e5f6-7890-abcd-ef1234567890)
(UUID(), 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'food-uuid-101', 1, 50000);