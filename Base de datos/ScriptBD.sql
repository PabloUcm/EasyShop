create database proyectois;
use proyectois;

create table Cliente(
id int not null auto_increment,
dni VARCHAR(9) not null,
nombre VARCHAR(50) not null,
telefono VARCHAR(15),
activo boolean default(true),
primary key(id)
);

create table Personal(
id int not null auto_increment,
dni VARCHAR(9) not null,
nombre VARCHAR(50) not null,
telefono VARCHAR(15) not null,
sueldo FLOAT not null,
horario VARCHAR(55) not null,
activo boolean default(true),
primary key(id)
);

create table Marca(
id int not null auto_increment,
cif VARCHAR(9) not null,
nombre VARCHAR(50) not null,
pais VARCHAR(30) not null,
activo boolean default(true),
primary key(id)
);


create table Producto(
id int not null auto_increment,
marca int not null,
upc VARCHAR(12) not null,
nombre VARCHAR(50) not null,
tipo VARCHAR(50) not null,
precio FLOAT not null,
cantidad int not null,
descripcion VARCHAR(255),
activo boolean default(true),
primary key(id),
foreign key (marca) references marca(id) ON UPDATE CASCADE
);


create table PC(
producto int not null,
procesador VARCHAR(30) not null,
ram VARCHAR(30) not null,
disco_duro VARCHAR(30) not null,
tarjeta_grafica VARCHAR(30) not null,
placa_base VARCHAR(30) not null,
primary key (producto),
foreign key(producto) references producto(id) ON UPDATE CASCADE
);


create table periferico(
producto int not null,
tipo_periferico VARCHAR(15) not null,
tipo_conexion VARCHAR(15) not null,
foreign key(producto) references producto(id) ON UPDATE CASCADE
);


create table venta(
id int not null auto_increment,
cliente int not null,
personal int not null,
fecha DATE not null,
importe FLOAT,
primary key(id),
foreign key(cliente) references Cliente(id)  ON UPDATE CASCADE,
foreign key(personal) references Personal(id)  ON UPDATE CASCADE
);


create table venta_producto(
venta int not null,
producto int not null,
nombre VARCHAR(50) not null,
unidades int not null,
precio_unitario FLOAT not null,
primary key(venta,producto),
foreign key(venta) references Venta(id)  ON UPDATE CASCADE,
foreign key(producto) references Producto(id)  ON UPDATE CASCADE
);

INSERT INTO Cliente (dni,nombre,telefono,activo) values("005","Alberto","+34 6800103390",true);
INSERT INTO Cliente (dni,nombre,telefono,activo) values("002","Daniel","+34 6830123443",true);
INSERT INTO Cliente (dni,nombre,telefono,activo) values("003","Jaime","+34 6834562783",true);
INSERT INTO Cliente (dni,nombre,telefono,activo) values("004","Miguel","+34 6920123312",true);
INSERT INTO Cliente (dni,nombre,telefono,activo) values("001","Pablo","+34 6800123233",true);
INSERT INTO Cliente (dni,nombre,telefono,activo) values("006","Pedro","+34 6800103494",false);


INSERT INTO Personal (dni,nombre,telefono,sueldo,horario,activo) values("100","Mariano","+34 6799103230",3000.00,"L-09:00/19:00",true);
INSERT INTO Personal (dni,nombre,telefono,sueldo,horario,activo) values("200","Antonio","+34 6893113242",2000.00,"M-09:00/19:00",true);
INSERT INTO Personal (dni,nombre,telefono,sueldo,horario,activo) values("300","Marina","+34 6899103230",2500.00,"X-09:00/19:00",true);
INSERT INTO Personal (dni,nombre,telefono,sueldo,horario,activo) values("400","Ana","+34 6899103230",1900.00,"J-09:00/19:00",false);


INSERT INTO Marca (cif,nombre,pais,activo) values("000","Msi","China",true);
INSERT INTO Marca (cif,nombre,pais,activo) values("001","Acer","Taiwán",true);
INSERT INTO Marca (cif,nombre,pais,activo) values("002","Asus","Taiwán",false);
INSERT INTO Marca (cif,nombre,pais,activo) values("003","Alienware","EEUU",true);
INSERT INTO Marca (cif,nombre,pais,activo) values("004","Apple","EEUU",true);


INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (1,"001","MSI GS66 Stealth","PC",2249.00,10,"Portatil gama alta.",true);
INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (1,"002","MSI GF75 Thin","PC",1399.00,20,"Portatil gama alta.",false);
INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (1,"003","MSI Infinite","PC",899.99,30,"Portatil gama media.",true);
INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (4,"005","Alienware Area51","PC",3000.00,50,"Sobremesa gama alta.",true);
INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (5,"006","MacBook Pro 13","PC",1499.99,70,"Portatil gama media.",true);
INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion,activo) values (5,"007","AirPods Pro","Periferico",279.00,100,"Cascos Apple.",true);


INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) values (1,"Intel i7-10750H","2x16GB DDR4","1TB SSD","RTX 2070","GS66");
INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) values (2,"Intel i7-9750H","2x8GB DDR4","512GB SSD","GTX 1650 ","GF75");
INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) values (3,"Intel i5-9400","8GB DDR4","2TB HDD","GTX 1060,","MS B9181 ITX");
INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) values (4,"Intel i7-7800K","32GB DDR4 ","4TB HDD","GTX 1080Ti","FCLGA 2066");
INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) values (5,"Inter i5 1,4Ghz","8GB LPDDR3","256GB SSD","Iris Plus Graphics 645","Pro 13");
INSERT INTO Periferico (producto,tipo_periferico,tipo_conexion) values (6, "Entrada/Salida","Bluetooth 5.0");