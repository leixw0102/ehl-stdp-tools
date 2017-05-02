1.默认
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name
  [(col_name data_type [COMMENT col_comment], ...)]
  [COMMENT table_comment]
  [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
  [CLUSTERED BY (col_name, col_name, ...)
  [SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS]
  [ROW FORMAT row_format]
  [STORED AS file_format]
  [LOCATION hdfs_path]

2.举例
   2.1CREATE TABLE pokes (foo INT, bar STRING);

   2.2 CREATE EXTERNAL TABLE page_view(viewTime INT, userid BIGINT,

     page_url STRING, referrer_url STRING,

     ip STRING COMMENT 'IP Address of the User',

     country STRING COMMENT 'country of origination')

 COMMENT 'This is the staging page view table'

 ROW FORMAT DELIMITED FIELDS TERMINATED BY '\054'

 STORED AS TEXTFILE

 LOCATION '<hdfs_location>';
   2.3 partion
     CREATE TABLE par_table(viewTime INT, userid BIGINT,

     page_url STRING, referrer_url STRING,

     ip STRING COMMENT 'IP Address of the User')

 COMMENT 'This is the page view table'

 PARTITIONED BY(date STRING, pos STRING)

ROW FORMAT DELIMITED '\t'

   FIELDS TERMINATED BY '\n'

STORED AS TEXTFILE;

   2.4 copy the empty 'table'
    CREATE TABLE empty_key_value_storen LIKE key_value_store;

3. 修改删除
 3.1 ALTER TABLE pokes ADD COLUMNS (new_col INT);
 3.2 ALTER TABLE invites ADD COLUMNS (new_col2 INT COMMENT 'a comment');
 3.3 ALTER TABLE events RENAME TO 3koobecaf;

4.load DATA
LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]
example :LOAD DATA LOCAL INPATH './examples/files/kv1.txt' OVERWRITE INTO TABLE pokes;

