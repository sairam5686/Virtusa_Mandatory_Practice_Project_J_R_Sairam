show databases;
create database virtusa_project ;
use virtusa_project ;
create table if not exists Customers( customer_id int primary key  ,name varchar(1000) , city varchar(500));
create table if not exists Products(product_id int primary key , name varchar(100) , category varchar(100) , price int );
create table if not exists Orders( order_id int primary key , customer_id int , date date);
create table if not exists Order_Items(order_id int , product_id int , quantity int );

-- insertion querys
insert into Customers values (1 , "sairam" , "Chennai") , (2 , "Vimal" , "Vellore" ) , (3 , "Sridharan" , "Vellore") , (4 , "Sharmi" , "Mumbai")  , (5 , "Vishal" , "Hyderabad"),(6 , "Vishnu" , "Mumbai") , (7 ,"Vasha" , "Chennai" ) , (8 , "Swetha" , "Madurai") , (9 , "Aditi" , "Salem")    , (10 , "Rahul" , "Chennai");                            
insert into Products values (100, 'iphone 15' , 'Electronics' , 100000),(101 , 'Samsung Galaxy', 'Electronics' , 9000),(102 , 'Lenovo Laptop' , 'Electronics' , 50000),(103 , 'JBL Smart Watch' , 'Electronics' , 4000),(104 , 'Boat Headphones' , 'Electronics' , 2100),(105 , 'Running Shoes' , 'Fashion' , 3501),(106 , 'T-Shirt' , 'Fashion' , 800),(107 , 'Jeans' , 'Fashion' , 1570),(108 , 'Backpack' , 'Accessories' , 1206),(109 , 'Sunglasses' , 'Accessories' , 1800),(110 , 'Coffee Maker' , 'Home Appliances' , 4500),(111 , 'Mixer Grinder' , 'Home Appliances' ,3520),(112 , 'Office Chair' , 'Furniture' ,7000),(113 , 'Study Table' , 'Furniture' , 8500),(114 , 'Water Bottle' , 'Accessories' , 500);
insert into Orders values (1 , 1 , '2024-01-05'),
(2 , 2 , '2026-01-10'),
(3 , 3 , '2024-01-15'),
(4 , 1 , '2024-02-02'),
(5 , 4 , '2024-02-10'),
(6 , 5 , '2024-02-18'),
(7 , 6 , '2024-03-01'),
(8 , 7 , '2024-03-05'),
(9 , 8 , '2024-03-12'),
(10 , 2 , '2024-03-20'),
(11 , 9 , '2024-04-01'),
(12 , 10 , '2024-04-05'),
(13 , 3 , '2024-04-10'),
(14 , 5 , '2024-04-15'),
(15 , 6 , '2024-04-20'),
(16 , 7 , '2024-05-02'),
(17 , 8 , '2024-05-08'),
(18 , 1 , '2024-05-12'),
(19 , 4 , '2024-05-18'),
(20 , 2 , '2024-05-25');

insert into Orders values (21 , 3 , '2026-01-05'),
(22 , 7 ,'2026-01-10'),
(23 , 2,'2026-01-15'),

(24 , 5 , '2026-02-02'),
(25 , 1 , '2026-02-12'),
(26 , 9 , '2026-02-18'),
(27 , 4 , '2026-03-03'),
(28 , 6 , '2026-03-10'),
(29 , 8 , '2026-03-20'),
(30 , 10 , '2026-04-01'),
(31 , 3 , '2026-04-07'),
(32 , 7 , '2026-04-15'),
(33 , 2 , '2026-05-02'),
(34 , 5 , '2026-05-10'),
(35 , 1 , '2026-05-18');

insert into Order_Items values (1,100,1) , (1,108,2) ,(2, 101, 1),(2 ,109, 1), (3,102,1),(3,114,3),(4,104,2),(4,  106,1),(5,105,2),(5,107 ,1) , (6,110,1), (6,114,2), (7,101,1),(7,108,1),(8,103,1),(8,109,2),(9,104,1),(9,114,4),(10,100,1),(10,108,1),(11,102,1),(11,107,2),(12,103,1),(12,114,2),(13,106,3),(14,105,1),(14,108,1),(15,112,1),(16,104,2),(16,109,1),(17,106,2),(18,100,1),(18,103,1),(19,105,1),(19,108,2),(20,114,5),(21,101,1),(21,106,2),(22,102,1),(23,107,2),(24,110,1),(25,100,1),(25,109,1),(26,111,1),(27,108,2),(28,104,1),(28,105,1),(29,106,3),(30,113,1),(31,102,1),(32,103,1),(32,114,2),(33,101,1) ,(34,107,2),(35,112,1),(35,108,1);


-- for showing all the datas in the table 
select * from Customers;
select * from Products;
select * from Orders;
select * from Order_Items;


-- to Find top-selling products
select Products.name , sum(Order_Items.quantity) as "No of items sold" , Products.price from Order_Items left join Products on Order_Items.product_id = Products.product_id group by Order_Items.product_id order by sum(Order_items.quantity) desc;

-- for identifying most valuable customers
select Customers.name , sum(Order_Items.quantity * Products.price) as "Total amt spent by the customer"  from Customers join Orders on Customers.customer_id  = Orders.customer_id join Order_Items on Orders.order_id = Order_Items.order_id join Products on Order_Items.product_id = Products.product_id group by Customers.customer_id order by sum(Order_Items.quantity * Products.price) desc;

--to find Monthly (Sorted Yearly) revenue calculation descending order
select year(Orders.date) as "Year" , month(Orders.date) as "Month"  , sum(Products.price * Order_Items.quantity) as "Monthly revenue" from Orders join Order_Items on Orders.order_id = Order_Items.order_id join Products on Products.product_id = Order_Items.product_id group by year(Orders.date) , month(Orders.date) order by year(Orders.date) ,  month(Orders.date) desc ;

-- to find Yearly Revenue
select year(Orders.date) as "Year" , sum(Products.price * Order_Items.quantity) as "Yearly revenue" from Orders join Order_Items on Orders.order_id = Order_Items.order_id join Products on Products.product_id = Order_Items.product_id group by year(Orders.date) order by year(Orders.date) desc ;

-- to find Category wise sales 
select Products.category , sum(Order_Items.quantity) as "Total Items sold" from Order_Items join Products on Order_Items.product_id = Products.product_id group by Products.category order by sum(Order_Items.quantity) desc;

--to find inactive customers
select name from Customers where customer_id not in (select customer_id  from Orders);
