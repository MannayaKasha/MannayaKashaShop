INSERT INTO Accounts (USER_NAME, ACTIVE, PASSWORD, USER_ROLE) VALUES
  ('manager1', 1, '123', 'MANAGER'),
  ('client1', 1, '12345', 'CLIENT');

INSERT INTO Categories (Name) VALUES
  ('shirts'),
  ('jeans'),
  ('shoes');

INSERT INTO Products (CODE, NAME, PRICE, NOVELTY, CREATE_DATE, CATEGORY_ID) VALUES
  ('S001', 'New Look Slim', 100, 0, SYSDATE(), 'jeans'),
  ('S002', 'ASOS Slim Twill', 150, 1, SYSDATE(), 'shirts'),
  ('S003', 'Edwin ED-80 Slim', 70, 0, SYSDATE(), 'jeans'),
  ('S004', 'PUMA Ignite Dual', 240, 0, SYSDATE(), 'shoes'),
  ('S005', 'Farah Oxford Shirt', 320, 0, SYSDATE(), 'shirts'),
  ('S006', 'River Island Regular', 130, 0, SYSDATE(), 'shirts'),
  ('S007', 'PUMA Fashin Alt', 390, 1, SYSDATE(), 'shoes'),
  ('S008', 'Pull&Bear Slim', 260, 1, SYSDATE(), 'jeans'),
  ('S009', 'Reebok Ros', 210, 0, SYSDATE(), 'shoes'),
  ('S0010', 'Selected Homme', 170, 0, SYSDATE(), 'shirts');