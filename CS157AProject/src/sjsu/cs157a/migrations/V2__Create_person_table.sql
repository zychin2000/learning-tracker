# change the length of password to 100
ALTER TABLE `project157a`.`user_register`
    CHANGE COLUMN `password` `password` VARCHAR(100) NULL DEFAULT NULL ;