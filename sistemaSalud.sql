create database sistemaSalud;
use sistemaSalud;

create table pacientes(
	id_paciente int not null auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni varchar(8) not null,
    correo varchar(100) not null,
    contraseña varchar(50) not null
);

select * from pacientes;
drop table pacientes;
INSERT INTO pacientes (nombre, apellido, dni, correo, contraseña) VALUES
('Jazmin', 'Monteverde', '87654321', 'jazmin@gmail.com','jazmin123'),
('Michael', 'Reques', '87654321', 'michael@gmail.com',  'michael123');

create table doctores(
	id_doctor int not null auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    especialidad varchar(100) not null
);



select * from doctores;

create table citas (
	id_cita int not null auto_increment primary key,
    fecha date not null,
    hora time not null,
	dni varchar(8) not null,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    correo varchar(100) not null,
    doctor varchar(100) not null
);

select * from citas;
drop table citas




