# change the length of password to 100
ALTER TABLE `project157a`.`user_register`
    CHANGE COLUMN `password` `password` VARCHAR(100) NULL DEFAULT NULL ;
    
# Drop Column principle_id from note_meta
--ALTER TABLE `project157a`.`note_meta` 
--DROP COLUMN `principle_id`;

# add username into user_meta
--ALTER TABLE `project157a`.`user_meta` 
--ADD COLUMN `username` VARCHAR(45) NULL DEFAULT NULL AFTER `major`;

# changed order of each attributes to user_meta
ALTER TABLE `project157a`.`user_meta` 
CHANGE COLUMN `username` `username` VARCHAR(45) NULL DEFAULT NULL AFTER `user_id`,
CHANGE COLUMN `major` `major` VARCHAR(45) NULL DEFAULT NULL AFTER `bio`;