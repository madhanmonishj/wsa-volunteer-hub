CREATE TABLE IF NOT EXISTS organiser (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    dbs_required BOOLEAN,
    address VARCHAR(255),
    city VARCHAR(255),
    postal_code VARCHAR(20),
    landmark VARCHAR(255),
    roles_needed VARCHAR(255),
    rewards_offering VARCHAR(255),
    image VARCHAR(255),
    date DATE,
    start_time TIME,
    end_time TIME,
    accessibility_assistance_provided VARCHAR(255),
    organiser_id BIGINT,
    approved BOOLEAN,
    FOREIGN KEY (organiser_id) REFERENCES organiser(id)
    );
