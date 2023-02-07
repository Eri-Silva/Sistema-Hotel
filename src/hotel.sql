drop database if exists hotel;
create database if not exists hotel;
use hotel;

create table if not exists tb_quartos(
    qua_id int primary key auto_increment,
    qua_nome varchar(20) not null,
    qua_descricao varchar(50) not null,
    qua_status varchar(50) default 'Disponivel'

    );

create table if not exists tb_recepcionistas(
    rec_cpf bigInt(11) primary key,
    rec_nome varchar(50),
    rec_status varchar(15) default 'Ativo'
);
create table if not exists tb_checkins(
    kin_id int primary key auto_increment,
    kin_qua_id int not null,
    kin_rec_cpf bigInt not null,
    kin_data timestamp not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(kin_qua_id) references tb_quartos(qua_id),
    foreign key(kin_rec_cpf) references tb_recepcionistas(rec_cpf)
);
create table if not exists tb_checkouts(
    out_id int primary key auto_increment,
    out_kin_id int not null,
    out_qua_id int not null,
    out_rec_cpf bigInt not null,
    out_data timestamp not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(out_qua_id) references tb_quartos(qua_id),
    foreign key(out_rec_cpf) references tb_recepcionistas(rec_cpf),
    foreign key(out_kin_id) references tb_checkins(kin_id)
);

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER `hotel`.`tb_checkins_BEFORE_INSERT` BEFORE INSERT ON `tb_checkins` FOR EACH ROW
BEGIN
     if exists(select qua_status from tb_quartos, tb_checkins where kin_qua_id = qua_id and kin_qua_id = new.kin_qua_id and qua_status in ('Indisponivel', 'Descartado')) then
    signal sqlstate '45000' set message_text = 'O quarto está indisponivel';
    end if;

END //

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER `hotel`.`tb_checkouts_BEFORE_INSERT` BEFORE INSERT ON `tb_checkouts` FOR EACH ROW
BEGIN
   if ((select qua_status from tb_quartos, tb_checkouts where out_qua_id = qua_id and out_qua_id = new.out_qua_id) = 'Disponivel') then
    signal sqlstate '45000' set message_text = 'O quarto já esta disponivel';
  end if;
END //

delimiter ;


