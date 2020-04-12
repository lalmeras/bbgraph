-- Figure model

create table ${schema}.Figure (id int8 not null, jps text, name text, savedOn timestamp, template text, author_id int8, primary key (id));
alter table ${schema}.Figure add constraint FKbiwbagjaurhhtqk5pe5flqi1a foreign key (author_id) references ${schema}.BasicUser;
create sequence ${schema}.Figure_id_seq start 1 increment 1;

create table ${schema}.ArchivedFigure (id int8 not null, jps text, name text, savedOn timestamp, template text, version int4, figure_id int8, author_id int8, primary key (id));
create sequence ${schema}.ArchivedFigure_id_seq start 1 increment 1;
alter table ${schema}.ArchivedFigure add constraint FKk85s4s0tmni0ndf68dawl99kp foreign key (figure_id) references ${schema}.Figure;
alter table ${schema}.ArchivedFigure add constraint FKaqnggemgtjeqj6gjpvphraliq foreign key (author_id) references ${schema}.BasicUser;
