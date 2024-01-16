DROP TABLE IF EXISTS "item_item";
DROP TABLE IF EXISTS "order_item";

CREATE TABLE item_item (
  id varchar(20),
  name VARCHAR(250) NOT NULL,
  price BIGINT NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE order_item (
  id varchar(20),
  orderNo VARCHAR(250) NOT NULL,
  itemId varchar(20) NOT NULL,
  qty integer NOT NULL,
  PRIMARY KEY (id)
);