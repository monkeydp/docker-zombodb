DROP TABLE IF EXISTS "public"."products";
CREATE TABLE products (
    id int8 NOT NULL PRIMARY KEY,
    name text NOT NULL,
    keywords varchar(64)[],
    short_summary text,
    long_description zdb.fulltext,
    price bigint,
    inventory_count integer,
    discontinued boolean default false,
    availability_date date
);

INSERT INTO "public"."products" VALUES (nextval('hibernate_sequence'), '小夜灯', '{灯}', '智能小夜灯', '一款智能小夜灯, 支持定时开关', 10, 2200, 'f', '2021-04-17');
INSERT INTO "public"."products" VALUES (nextval('hibernate_sequence'), '灭蚊灯', '{灯,灭蚊}', '智能灭蚊灯', '一款智能灭蚊灯, 支持击杀统计', 30, 0, 't', '2021-11-23');
INSERT INTO "public"."products" VALUES (nextval('hibernate_sequence'), '灭火器', '{灭火}', '智能灭火器', '一款智能灭火器, 支持烟雾感应自动启动', 200, 1000, 'f', '2021-07-05');

CREATE INDEX idxproducts
ON products
USING zombodb ((products.*))
WITH (url='http://elastic:changeme@192.168.x.x:9200/');
