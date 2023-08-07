CREATE TABLE vehicle(
    id SERIAL PRIMARY KEY,
    name_vehicle TEXT NOT NULL ,
    brand TEXT NOT NULL,
    year_vehicle INTEGER NOT NULL,
    description TEXT,
    is_sold BOOLEAN NOT NULL DEFAULT FALSE,
    chassis TEXT UNIQUE NOT NULL,
    price NUMERIC NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP
);