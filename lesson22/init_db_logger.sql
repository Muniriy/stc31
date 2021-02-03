CREATE TABLE logger_db (
            log_id     varchar(100) PRIMARY KEY,
            entry_date timestamp,
            logger     varchar(100),
            log_level  varchar(10),
            message    varchar(100),
            exception  varchar(100)
);