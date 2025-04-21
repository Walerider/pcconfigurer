ALTER TABLE product_attributes
    DROP CONSTRAINT product_attributes_attribute_id_fkey;

ALTER TABLE product_attributes
    DROP CONSTRAINT product_attributes_attribute_value_id_fkey;

ALTER TABLE product_attributes
    DROP CONSTRAINT product_attributes_product_id_fkey;

ALTER TABLE product_prices
    DROP CONSTRAINT product_prices_product_id_fkey;

ALTER TABLE user_assemblies
    DROP CONSTRAINT user_assemblies_user_id_fkey;

ALTER TABLE user_assembly_components
    DROP CONSTRAINT user_assembly_components_assembly_id_fkey;

ALTER TABLE user_assembly_components
    DROP CONSTRAINT user_assembly_components_product_id_fkey;

CREATE TABLE attribute
(
    id   INTEGER NOT NULL,
    name TEXT,
    CONSTRAINT pk_attribute PRIMARY KEY (id)
);

CREATE TABLE attribute_value
(
    id    INTEGER NOT NULL,
    value TEXT,
    CONSTRAINT pk_attributevalue PRIMARY KEY (id)
);

CREATE TABLE product_attribute
(
    id                 INTEGER NOT NULL,
    product_id         INTEGER,
    attribute_id       INTEGER,
    attribute_value_id INTEGER,
    CONSTRAINT pk_productattribute PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id       INTEGER NOT NULL,
    username TEXT,
    email    TEXT,
    password TEXT,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE product_attribute
    ADD CONSTRAINT FK_PRODUCTATTRIBUTE_ON_ATTRIBUTE FOREIGN KEY (attribute_id) REFERENCES attribute (id);

ALTER TABLE product_attribute
    ADD CONSTRAINT FK_PRODUCTATTRIBUTE_ON_ATTRIBUTE_VALUE FOREIGN KEY (attribute_value_id) REFERENCES attribute_value (id);

ALTER TABLE product_attribute
    ADD CONSTRAINT FK_PRODUCTATTRIBUTE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

DROP TABLE attribute_values CASCADE;

DROP TABLE attributes CASCADE;

DROP TABLE product_attributes CASCADE;

DROP TABLE product_prices CASCADE;

DROP TABLE user_assemblies CASCADE;

DROP TABLE user_assembly_components CASCADE;

DROP TABLE users CASCADE;

ALTER TABLE products
    DROP COLUMN name;

ALTER TABLE categories
    ALTER COLUMN name TYPE TEXT USING (name::TEXT);