USE sea_market;
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       email VARCHAR(100),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE items (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       seller_id INT NOT NULL,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       price DECIMAL(10,2) NOT NULL,
                       status VARCHAR(20) DEFAULT 'ACTIVE',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (seller_id) REFERENCES users(id)
);
