CREATE DATABASE cafe_db DEFAULT CHARACTER SET utf8;

create user cafe_db@localhost
        identified by password;

GRANT SELECT,INSERT,UPDATE,DELETE
ON cafe_db.*
TO cafe_db@localhost;

GRANT SELECT,INSERT,UPDATE,DELETE
ON cafe_db.*
TO cafe_user@'%';
#IDENTIFIED BY 'cafe_password';