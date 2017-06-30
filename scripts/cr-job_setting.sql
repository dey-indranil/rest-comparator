CREATE TABLE job_setting 
(job_name VARCHAR(20), 
url1 VARCHAR(200), 
url2 VARCHAR(200), 
is_active VARCHAR(1), 
modified_dt DATETIME default current_timestamp,
PRIMARY KEY(job_name))