INSERT INTO users (
    id,
    login,
	password,
	role
) VALUES (
    2,
    'manager_01',
    '948596tjkjg',
    1
), (
    3,
    'cook_01',
    '05090q39lkl',
    1
), (
    4,
    'cook_02',
    'sdlkfbl94308',
    1
), (
    5,
    'client_01',
    'w459oklzdfo93',
    2
);

INSERT INTO user_info (
    id,
    name,
    surname,
    phone,
    email
) VALUES (
    1,
    'Admin',
    'Admin',
    '+375296006060',
    'admin@gmail.com'
), (
    2,
    'Alina',
    'Vukina',
    '+375295443434',
    'alina_vukina@gmail.com'
), (
    3,
    'Amin',
    'Kulev',
    '+375446323424',
    'amin_kulev@gmail.com'
), (
    4,
    'Karina',
    'Horosheva',
    '+375297685746',
    'karina_horosheva@gmail.com'
), (
    5,
    'John',
    'Gordeev',
    '+375442637583',
    'john_gordeev@gmail.com'
);


INSERT INTO workers (
    identity,
    start_of_work,
    specialization
) VALUES (
    3,
    '2020-09-11',
    'meat'
), (
    5,
    '2018-12-15',
    'bakery'
);

insert into ingredients
(identity, title)
values
( 1, 'rice'),
(2, 'pasta'),
(3, 'potato'),
(4, 'apple'),
(5, 'banana'),
(6, 'strawberry'),
(7, 'raspberry'),
(8, 'tomato'),
(9, 'cucumber'),
(10, 'pepper'),
(11, 'chicken'),
(12, 'tuna'),
(13, 'salmon'),
(14, 'shrimp'),
(15, 'honey'),
(16, 'tomato souse'),
(17, 'green tea'),
(18, 'coffee'),
(19, 'milk'),
(20, 'olive oil'),
(21, 'salt')

insert into foods
(title, description, price, type)
values
(
    'Rice with chicken',
    'boil rice, fry chicken in olive oil, salt and pepper',
    12.5,
    1
), (
    'Fruit salad',
    'slice all the fruits, add honey, mix it',
    13.2,
    1
), (
    'Milkshake',
    'slice berries, add honey, mix it in blender for 4 minutes',
    4.5,
    2
)

INSERT INTO foods_ingredients
(food_id, ingredient_id, ingredient_amount)
VALUES
(1, 1, '100 grams'),
(1, 11, '300 grams'),
(1, 20, 'a tablespoon'),
(1, 21, 'a teaspoon'),
(1, 10, 'a teaspoon'),
(2, 4, '1'),
(2, 5, '1'),
(2, 6, '5'),
(2, 7, '8'),
(2, 15, 'a tablespoon'),
(3, 6, '4'),
(3, 19, '200 ml'),
(3, 15, 'a teaspoon')

INSERT INTO orders
(worker_id, client_id, client_name, food_id)
VALUES
(3, 5, 'John', 1),
(2, 5, 'John', 3)

INSERT INTO orders
(worker_id, client_name, food_id)
VALUES
(3, 'Dina', 2)