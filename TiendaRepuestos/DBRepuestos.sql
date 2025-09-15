Drop database if exists DBRepuestos;
Create database DBRepuestos;
Use DBRepuestos;

create table clientes(
	id_cliente int auto_increment,
    nombre_cliente varchar(150),
    direccion_cliente varchar(255), 
    telefono_cliente varchar(150), 
    email_cliente varchar(150),
    PRIMARY KEY PK_id_cliente (id_cliente)
);

create table repuestos(
	id_repuesto int auto_increment,
    nombre_repuesto varchar(150),
    marca varchar(150),
    modelo varchar(150),
    precio decimal(10,2),
    stock int,
    PRIMARY KEY PK_id_repuesto (id_repuesto)
);

create table proveedores(
	id_proveedor int auto_increment,
	nombre_empresa varchar(150),
	contacto varchar(50),
	telefono_proveedor varchar(20), 
	direccion_proveedor varchar(255),
	PRIMARY KEY PK_id_proveedor (id_proveedor)
);

create table ventas(
	id_venta int auto_increment, 
    id_cliente int, 
    id_repuesto int,
    fecha date,
	cantidad int, 
    total decimal(10,2),
    PRIMARY KEY PK_id_venta (id_venta),
    CONSTRAINT fk_id_cliente FOREIGN KEY (id_cliente)
        REFERENCES clientes (id_cliente),
	CONSTRAINT fk_id_repuestos FOREIGN KEY (id_repuesto)
        REFERENCES repuestos (id_repuesto)
);

-- Procedimientos almacenados para agregar a las entidades:

-- Agregar clientes
Delimiter $$
create procedure sp_agregar_cliente(
	in c_nombre varchar(150),
    in c_direccion varchar(255),
    in c_telefono varchar(150),
    in c_email varchar(150)
    )
    Begin
    insert into clientes(nombre_cliente, direccion_cliente, telefono_cliente, email_cliente)
    values(c_nombre, c_direccion, c_telefono, c_email);
    End$$
Delimiter ;

CALL sp_agregar_cliente('Carlos Ramírez', 'Zona 1, Guatemala', '5555-1001', 'carlosramirez@mail.com');
CALL sp_agregar_cliente('Ana López', 'Zona 10, Guatemala', '4444-1002', 'analopez@mail.com');
CALL sp_agregar_cliente('Luis Pérez', 'Mixco, Guatemala', '3333-1003', 'luisperez@mail.com');
CALL sp_agregar_cliente('María González', 'Antigua Guatemala', '6666-1004', 'mariagonzalez@mail.com');
CALL sp_agregar_cliente('Pedro Castillo', 'San Juan Sacatepéquez', '7777-1005', 'pedrocastillo@mail.com');
CALL sp_agregar_cliente('Sofía Morales', 'Villa Nueva', '8888-1006', 'sofiamorales@mail.com');
CALL sp_agregar_cliente('José Martínez', 'Chimaltenango', '9999-1007', 'josemartinez@mail.com');
CALL sp_agregar_cliente('Lucía Torres', 'Jalapa', '1234-1008', 'luciatorres@mail.com');
CALL sp_agregar_cliente('Ricardo Chávez', 'Antigua', '2345-1009', 'ricardochavez@mail.com');
CALL sp_agregar_cliente('Elena Ruiz', 'Quetzaltenango', '3456-1010', 'elenaruiz@mail.com');

Delimiter $$
create procedure sp_agregar_repuesto(
	in r_nombre varchar(150),
    in r_marca varchar(255),
    in r_modelo varchar(150),
    in r_precio decimal(10,2),
    in r_stock int
    
    )
    Begin
    insert into repuestos(nombre_repuesto, marca, modelo, precio, stock)
    values(r_nombre, r_marca, r_modelo, r_precio, r_stock);
    End$$
Delimiter ;

CALL sp_agregar_repuesto('Filtro de aceite', 'Toyota', 'Corolla 2015', 75.50, 20);
CALL sp_agregar_repuesto('Batería', 'Panasonic', 'N-40L', 850.00, 10);
CALL sp_agregar_repuesto('Pastillas de freno', 'Brembo', 'Civic 2018', 320.75, 15);
CALL sp_agregar_repuesto('Amortiguador', 'Monroe', 'Hilux 2020', 650.00, 8);
CALL sp_agregar_repuesto('Aceite sintético', 'Mobil', '5W-30', 120.00, 50);
CALL sp_agregar_repuesto('Filtro de aire', 'K&N', 'Universal', 300.00, 25);
CALL sp_agregar_repuesto('Correa de distribución', 'Gates', 'Fit 2014', 220.00, 12);
CALL sp_agregar_repuesto('Radiador', 'Valeo', 'Altima 2019', 1100.00, 5);
CALL sp_agregar_repuesto('Farol delantero', 'Bosch', 'Camry 2018', 450.00, 7);
CALL sp_agregar_repuesto('Bomba de agua', 'Mitsubishi', 'Lancer 2016', 500.00, 6);


Delimiter $$
create procedure sp_agregar_proveedores(
	in pv_nombre varchar(150),
    in pv_contacto varchar(255),
    in pv_telefono varchar(150),
    in pv_direccion varchar(155)
    
    )
    Begin
    insert into proveedores(nombre_empresa, contacto, telefono_proveedor, direccion_proveedor)
    values(pv_nombre, pv_contacto, pv_telefono , pv_direccion);
    End$$
Delimiter ;
-- drop procedure sp_agregar_proveedores;

CALL sp_agregar_proveedores('Autopartes S.A.', 'Laura Méndez', '2200-1001', 'Zona 4, Guatemala');
CALL sp_agregar_proveedores('Repuestos y Más', 'Jorge Escobar', '2300-1002', 'Zona 12, Guatemala');
CALL sp_agregar_proveedores('Distribuidora El Motor', 'Lucía Gómez', '2400-1003', 'Chimaltenango');
CALL sp_agregar_proveedores('Inyección Automotriz', 'Marco Silva', '2500-1004', 'Jalapa');
CALL sp_agregar_proveedores('Soluciones Auto Part', 'Patricia López', '2100-1005', 'Quetzaltenango');
CALL sp_agregar_proveedores('Partes Exprés', 'Oscar Pérez', '2600-1006', 'Escuintla');
CALL sp_agregar_proveedores('MotorCity', 'Ana Beltrán', '2700-1007', 'Huehuetenango');
CALL sp_agregar_proveedores('Tableros y Luces', 'José Mendoza', '2800-1008', 'Sacatepéquez');
CALL sp_agregar_proveedores('Repuestos del Norte', 'Carlos López', '2900-1009', 'Izabal');
CALL sp_agregar_proveedores('Auto Equipos GT', 'Mario Ruiz', '3000-1010', 'Petén');

Delimiter $$
create procedure sp_agregar_ventas(
	in v_id_cliente int,
    in v_id_repuesto int,
    in v_fecha date,
    in v_cantidad int,
    in v_total decimal(10,2)
    )
    Begin
    insert into ventas(id_cliente, id_repuesto, fecha, cantidad, total)
    values(v_id_cliente, v_id_repuesto, v_fecha , v_cantidad, v_total);
    End$$
Delimiter ;
-- drop procedure sp_agregar_ventas;

CALL sp_agregar_ventas(1, 1, '2025-09-14', 2, 151.00);
CALL sp_agregar_ventas(2, 2, '2025-09-14', 1, 850.00);
CALL sp_agregar_ventas(1, 3, '2025-09-13', 3, 962.25);
CALL sp_agregar_ventas(3, 4, '2025-09-12', 1, 650.00);
CALL sp_agregar_ventas(4, 5, '2025-09-11', 5, 600.00);
CALL sp_agregar_ventas(5, 6, '2025-09-10', 2, 600.00);
CALL sp_agregar_ventas(6, 7, '2025-09-09', 2, 440.00);
CALL sp_agregar_ventas(7, 8, '2025-09-08', 1, 1100.00);
CALL sp_agregar_ventas(8, 9, '2025-09-07', 3, 1350.00);
CALL sp_agregar_ventas(9, 10, '2025-09-06', 2, 900.00);


select * from clientes;
select * from repuestos;
select * from proveedores;
select * from ventas;