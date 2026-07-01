CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS food_item (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    tag VARCHAR(100),
    rating INT DEFAULT 3,
    status INT DEFAULT 1,
    last_eat_time DATETIME,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS food_history (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    date DATE NOT NULL,
    is_chosen INT DEFAULT 0,
    note VARCHAR(500),
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX IF NOT EXISTS idx_food_item_user_id ON food_item(user_id);
CREATE INDEX IF NOT EXISTS idx_food_history_user_id ON food_history(user_id);
CREATE INDEX IF NOT EXISTS idx_food_history_date ON food_history(date);