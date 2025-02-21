CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    weather TEXT NOT NULL,
    clean_air FLOAT CHECK (clean_air BETWEEN 0 and 10)
);

CREATE TABLE purpose (
    id SERIAL PRIMARY KEY,
    location_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    degree_of_competition FLOAT CHECK (degree_of_competition BETWEEN 0 AND 10),
    impact_on_plans FLOAT CHECK (impact_on_plans BETWEEN 0 AND 10),
    FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE CASCADE
);

CREATE TABLE next_purpose (
    id SERIAL PRIMARY KEY,
    location_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    difficulty FLOAT CHECK (difficulty BETWEEN 0 AND 10),
    significance FLOAT CHECK (significance BETWEEN 0 AND 10),
    FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE CASCADE
);

CREATE TABLE character(
    id SERIAL PRIMARY KEY,
    location_id INT NOT NULL,
    purpose_id INT NOT NULL,
    next_purpose_id INT,
    name VARCHAR(100) NOT NULL,
    purpose TEXT NOT NULL,
    lvl_of_satisf FLOAT CHECK (lvl_of_satisf BETWEEN 0 AND 10),
    FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE CASCADE,
    FOREIGN KEY (purpose_id) REFERENCES purpose (id) ON DELETE CASCADE,
    FOREIGN KEY (next_purpose_id) REFERENCES next_purpose (id) ON DELETE SET NULL
);
