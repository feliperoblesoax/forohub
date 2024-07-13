create table respuestas(
    id bigint not null auto_increment,
    topico_id bigint not null,
    usuario_id bigint not null,
    respuesta varchar(300) not null,
    data datetime not null,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_usuario_id foreign key(usuario_id) references usuarios(id)

)