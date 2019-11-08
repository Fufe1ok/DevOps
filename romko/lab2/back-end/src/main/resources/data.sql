INSERT INTO ROLES VALUES
(1, 'DRIVER'),
(2, 'RENTER');

INSERT INTO LOCATIONS VALUES
(1, 'Located near the Lviv Airport','Parking Danylo Halytskyi'),
(2, 'Right in the city centre','Parking Central'),
(3, 'Near Chervonoyi Kalyny Ave','Parking Syhiv'),
(4, 'Do not park here please', 'Parking Levanda');

INSERT INTO USERS VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin0@gmail.com', 'Petro', 'Ivaniuk', 'admin', 1),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'user0@gmail.com', 'Ihor', 'Vasiuk', 'user', 2);

INSERT INTO FARES VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PAID', 'UA265222221131', 1),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE', 'UA235232214122', 2);

INSERT INTO CARS VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORANGE', 'Lamborghini Reventon', 'BC5503HB', 'ELITE', 2, 1 ),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'WHITE', 'Opel Kadet', 'AO2200HI', 'ECONOM', 2, 1 ),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLACK', 'Audi A4', 'AA0110BA', 'COMFORT', 2, 1 );
