CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS food_item (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    tag VARCHAR(100),
    rating INTEGER DEFAULT 3,
    status INTEGER DEFAULT 1,
    meal_type VARCHAR(20) DEFAULT 'ALL',
    last_eat_time TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS food_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    date DATE NOT NULL,
    meal_type VARCHAR(20),
    is_chosen INTEGER DEFAULT 0,
    note VARCHAR(500),
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_food_item_user_id ON food_item(user_id);
CREATE INDEX IF NOT EXISTS idx_food_history_user_id ON food_history(user_id);
CREATE INDEX IF NOT EXISTS idx_food_history_date ON food_history(date);