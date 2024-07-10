create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(250) not null unique,
    fecha_creacion varchar(100) not null,
    estatus varchar(100) not null,
    activo bigint not null,
    nombre_del_curso varchar(100) not null,

    primary key(id)

)