DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    CID     VARCHAR(10),
    Company VARCHAR(40),
    City    VARCHAR(40),
    Amount  numeric(12,2)
);

insert into customers values ('001', 'Sazons', 'Cullowhee', 25.0);
insert into customers values ('002', 'Mad Batter', 'Sylva', 30.0);
insert into customers values ('003', 'City Lights', 'Sylva', 28.0);



