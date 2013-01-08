SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS "actions" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "description" varchar(90) DEFAULT NULL,
  "notes" text,
  "status_id" int(11) NOT NULL,
  "context_id" int(11) DEFAULT NULL,
  "project_id" int(11) DEFAULT NULL,
  "action_date" date DEFAULT NULL,
  "statuschange_date" date DEFAULT NULL,
  "done" tinyint(1) DEFAULT '0',
  PRIMARY KEY ("id"),
  KEY "fk_actions_statuses_idx" ("status_id"),
  KEY "fk_actions_contexts1_idx" ("context_id"),
  KEY "fk_actions_projects1_idx" ("project_id")
);

CREATE TABLE IF NOT EXISTS "contexts" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "projects" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  "notes" text NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "statuses" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "name" varchar(30) DEFAULT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "thoughts" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "notes" text,
  PRIMARY KEY ("id")
);


ALTER TABLE `actions`
  ADD CONSTRAINT "fk_actions_contexts" FOREIGN KEY ("context_id") REFERENCES "contexts" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT "fk_actions_projects" FOREIGN KEY ("project_id") REFERENCES "projects" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT "fk_actions_statuses" FOREIGN KEY ("status_id") REFERENCES "statuses" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
