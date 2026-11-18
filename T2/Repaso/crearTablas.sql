USE repaso;

CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE articulo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    precio DECIMAL(10,2) UNSIGNED NOT NULL,
    stock INT UNSIGNED NOT NULL,
    categoria_id INT NOT NULL,
    CONSTRAINT fk_articulo_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria(id)
);


CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unidades INT UNSIGNED NOT NULL CHECK (unidades > 0),
    precio_unitario DECIMAL(10,2) UNSIGNED NOT NULL,
    articulo_id INT NOT NULL,
    CONSTRAINT fk_venta_articulo
        FOREIGN KEY (articulo_id)
        REFERENCES articulo(id)
);

INSERT INTO categoria (nombre) VALUES
    ('Electronica'),
    ('Libros'),
    ('Videojuegos'),
    ('Ropa');


INSERT INTO articulo (nombre, precio, stock, categoria_id) VALUES
    ('Portatil HP', 350.00, 3, 1),
    ('Monitor Samsung', 120.50, 5, 1),
    ('Teclado Logitech', 25.99, 0, 1),

    ('Don Quijote', 12.50, 8, 2),
    ('El Principito', 8.99, 4, 2),
    ('Cien años de soledad', 15.00, 0, 2),

    ('Minecraft PS4', 19.99, 6, 3),
    ('FIFA 23', 14.50, 2, 3),
    ('Zelda BOTW', 35.00, 1, 3);