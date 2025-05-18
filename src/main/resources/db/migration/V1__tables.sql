
CREATE TABLE brands (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        country VARCHAR(255)
);

CREATE INDEX idx_brand_name ON brands(name);


CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            description TEXT
);

CREATE INDEX idx_category_name ON categories(name);


CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL UNIQUE,
                          description TEXT,
                          price DECIMAL(19,2) CHECK (price > 0),
                          stock_quantity INT CHECK (stock_quantity >= 0),
                          created_at DATE,
                          updated_at DATE,
                          category_id INT,
                          brand_id INT,
                          FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
                          FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE CASCADE
);

CREATE INDEX idx_product_title ON products(title);


CREATE TABLE specs (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       value TEXT,
                       product_id BIGINT,
                       FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE INDEX idx_spec_product ON specs(product_id);


CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        total_amount DECIMAL(19,2) CHECK (total_amount > 0),
                        email VARCHAR(255) NOT NULL,
                        status VARCHAR(50),
                        created_at DATE,
                        updated_at DATE
);

CREATE INDEX idx_order_email ON orders(email);


CREATE TABLE items (
                       id SERIAL PRIMARY KEY,
                       quantity INT CHECK (quantity >= 0),
                       price_at_purchase DECIMAL(19,2) CHECK (price_at_purchase > 0),
                       product_id BIGINT,
                       order_id BIGINT,
                       FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
                       FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE INDEX idx_item_order ON items(order_id);
CREATE INDEX idx_item_product ON items(product_id);


