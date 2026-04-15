show databases;
create database virtusa_project ;
use virtusa_project ;
create table if not exists Customers( customer_id int primary key  ,name varchar(1000) , city varchar(500));
create table if not exists Products(product_id int primary key , name varchar(100) , category varchar(100) , price int );
create table if not exists Orders( order_id int primary key , customer_id int , date date);
create table if not exists Order_Items(order_id int , product_id int , quantity int );


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
