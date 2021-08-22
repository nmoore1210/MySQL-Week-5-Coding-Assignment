CREATE DATABASE IF NOT EXISTS music;

use music;

drop table if exists music_artists;

Create table music_artists (

	id int(10) not null auto_increment,
	Artist_Name varchar(100) not null,
	Origin_Year int(10) not null,
	Total_Albums int(10) not null,
	primary key (id)

);

INSERT INTO music_artists (Artist_Name, Origin_Year, Total_Albums)
VALUES ('Tycho', 2002, 6);

INSERT INTO music_artists (Artist_Name, Origin_Year, Total_Albums)
VALUES ('Fleetwood Mac', 1967, 17);

INSERT INTO music_artists (Artist_Name, Origin_Year, Total_Albums)
VALUES ('TAME IMPALA', 2007, 4);



