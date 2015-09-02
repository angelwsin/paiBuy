CREATE TABLE `NewTable` (
`id`  varchar(32) NOT NULL ,
`login_name`  varchar(100) NOT NULL UNIQUE,
`pwd`  varchar(100) NOT NULL ,
`add_time`  datetime NOT NULL ,
PRIMARY KEY (`id`)
)
;