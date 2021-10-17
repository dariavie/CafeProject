CREATE TABLE users (
    id INTEGER NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL UNIQUE,
    password NCHAR(32) NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - работник (Role.WORKER)
     * 2 - клиент (Role.CLIENT)
     */
    role TINYINT NOT NULL CHECK (role IN (0, 1, 2)),
    CONSTRAINT users_pk
    PRIMARY KEY (id)
);

CREATE TABLE user_info (
	id INTEGER NOT NULL,
	surname VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	phone VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT user_info_pk
	PRIMARY KEY (id),
	CONSTRAINT user_info_user_fk
    FOREIGN KEY (id)
    REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE workers (
    id INTEGER NOT NULL,
    start_of_work DATE NOT NULL,
    end_of_work DATE NULL,
    specialization VARCHAR(50) NOT NULL,
    CONSTRAINT workers_pk
    PRIMARY KEY (id),
    CONSTRAINT workers_user_fk
    FOREIGN KEY (id)
    REFERENCES user_info (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE ingredients (
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT ingredients_pk
    PRIMARY KEY(id)
);

CREATE TABLE foods (
	id INTEGER NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL UNIQUE,
	description VARCHAR(500) NOT NULL,
	price DOUBLE NOT NULL,
	recipe VARCHAR(500) NOT NULL,

	/*
     * 1 - dish
     * 2 - drink
     */
    type TINYINT NOT NULL CHECK (type IN (1, 2)),
    CONSTRAINT foods_pk
	PRIMARY KEY (id)
);

CREATE TABLE foods_ingredients (
    food_id INTEGER NOT NULL,
    ingredient_id INTEGER NOT NULL,
    ingredient_amount VARCHAR(50) NOT NULL,
    CONSTRAINT foods_ingredients_food_fk
    FOREIGN KEY (food_id)
    REFERENCES foods (id)
    ON UPDATE CASCADE
	ON DELETE RESTRICT,
	CONSTRAINT foods_ingredients_ingredient_fk
	FOREIGN KEY (ingredient_id)
	REFERENCES ingredients (id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE orders (
    id INTEGER NOT NULL AUTO_INCREMENT,
    client_id INTEGER NOT NULL,
    price DOUBLE not null,
    /*
    1 - in progress
    2 - done
    */
    status TINYINT NOT NULL CHECK (role in (1,2))
    CONSTRAINT orders_pk
    PRIMARY KEY (id),
	CONSTRAINT orders_client_fk
	FOREIGN KEY (client_id)
    REFERENCES user_info (id)
    ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE orders_foods (
    id INTEGER not null auto_increment
    order_id integer not null,
    food_id integer not null,
    constraint orders_foods_pk
    primary key (id),
    constraint orders_foods_order_fk
    foreign key (order_id)
    references orders (id)
    on update cascade
    on delete restrict,
    constraint orders_foods_food_fk
    foreign key (food_id)
    references foods (id)
    on update cascade
    on delete restrict
);
