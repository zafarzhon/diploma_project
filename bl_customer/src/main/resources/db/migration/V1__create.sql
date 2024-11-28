CREATE SEQUENCE IF NOT EXISTS customer_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS order_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS product_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE customers
(
    id         BIGINT            NOT NULL,
    login      VARCHAR(20)       NOT NULL,
    password   VARCHAR(255),
    balance    INTEGER DEFAULT 0 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id          BIGINT       NOT NULL,
    customer_id BIGINT,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    status      VARCHAR(255) NOT NULL,
    total_price INTEGER,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id         BIGINT NOT NULL,
    product_id INTEGER,
    quantity   INTEGER,
    price      INTEGER,
    order_id   BIGINT,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_login UNIQUE (login);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);