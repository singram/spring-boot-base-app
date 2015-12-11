ALTER TABLE person
ADD COLUMN `created_at` DATETIME NOT NULL AFTER `last_name`,
ADD COLUMN `updated_at` DATETIME NOT NULL AFTER `created_at`;

RENAME TABLE `person` TO `people`;

ALTER TABLE thoughts
ADD COLUMN `created_at` DATETIME NOT NULL AFTER `data`,
ADD COLUMN `updated_at` DATETIME NOT NULL AFTER `created_at`,
DROP COLUMN `creation_date`;