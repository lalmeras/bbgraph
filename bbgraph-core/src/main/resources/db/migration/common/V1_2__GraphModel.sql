-- Graph model

create table bbgraph.ArchivedGraph (id int8 not null, jps text, name text, savedOn timestamp, template text, version int4, graph_id int8, primary key (id));
create table bbgraph.Graph (id int8 not null, jps text, name text, savedOn timestamp, template text, primary key (id));
alter table bbgraph.Parameter drop constraint UK_6y3o26o4ukqaqks2k5wo76ua7;
alter table bbgraph.Parameter add constraint UK_6y3o26o4ukqaqks2k5wo76ua7 unique (name);
create sequence bbgraph.ArchivedGraph_id_seq start 1 increment 1;
create sequence bbgraph.Graph_id_seq start 1 increment 1;
alter table bbgraph.ArchivedGraph add constraint FKk85s4s0tmni0ndf68dawl99kp foreign key (graph_id) references bbgraph.Graph;
