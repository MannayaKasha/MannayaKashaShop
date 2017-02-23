CREATE DATABASE `shop_mannaya_kasha`
  DEFAULT CHARACTER SET utf8;

GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, ALTER
ON `shop_mannaya_kasha`.*
TO mannaya_kasha@localhost
IDENTIFIED BY 'mannaya_kasha_password';

GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, ALTER
ON `shop_mannaya_kasha`.*
TO mannaya_kasha@'%'
IDENTIFIED BY 'mannaya_kasha_password';