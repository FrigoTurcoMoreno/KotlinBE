CREATE TABLE IF NOT EXISTS users (
  id binary(16) PRIMARY KEY,
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255) NOT NULL,
  is_admin bit(1) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL
);


INSERT IGNORE INTO users (id, email, first_name, is_admin, last_name, password) VALUES
    (0x76A4CAF42C0D46B1AB387FB187002C77, 'mft@gmail.com', 'Moreno', 1, 'Frigo Turco', '$2a$11$/otQp40iPlXTqd.CbKnePO4Eomr.cvuVG.B4LgjLLTNtahssQ5ylm'),
    (0xA9F91E2106AD48A79413674DA9AFF35B, 'ldc@gmail.com', 'Luca', 0, 'Da Corte', '$2a$11$llGjZZRmhZ0wb3noZcvxjeWDEmbLKhL79nm1Nr2vWRxlDD6FZ3dGW');
