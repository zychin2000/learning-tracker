

--# change the length of password to 100
--ALTER TABLE `project157a`.`user_register`
--    CHANGE COLUMN `password` `password` VARCHAR(100) NULL DEFAULT NULL ;
--  
--
--# changed note_id to AUTO_INCREMENT
--ALTER TABLE `project157a`.`note_meta` 
--CHANGE COLUMN `note_id` `note_id` INT NOT NULL AUTO_INCREMENT ;
--
--# changed data_type from  VARCHAR to INT
--ALTER TABLE `project157a`.`note_meta` 
--CHANGE COLUMN `class_id` `class_id` INT(45) NOT NULL ;
--
--ALTER TABLE `project157a`.`note_docu` 
--CHANGE COLUMN `note_id` `note_id` INT NOT NULL ;
--
--ALTER TABLE `project157a`.`note_picture` 
--CHANGE COLUMN `link` `link` MEDIUMBLOB NULL DEFAULT NULL ;