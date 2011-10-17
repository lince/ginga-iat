drop database dw;
create database dw;
use dw;
CREATE TABLE Dim_Data (
  idDim_Data INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  dia INTEGER UNSIGNED NULL,
  mes INTEGER UNSIGNED NULL,
  ano INTEGER UNSIGNED NULL,
  dia_semana VARCHAR(13) NULL,
  semana INTEGER UNSIGNED NULL,
  quinzena INTEGER UNSIGNED NULL,
  trimestre INTEGER UNSIGNED NULL,
  bimestre INTEGER UNSIGNED NULL,
  semestre INTEGER UNSIGNED NULL,
  ferias_escolares BOOL NULL,
  feriado_nacional VARCHAR(15) NULL,
  datacompleta DATETIME NULL,
  PRIMARY KEY(idDim_Data)
);

CREATE TABLE Dim_Device (
  idDim_Device INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  serial_number VARCHAR(30) NULL,
  type_ VARCHAR(30) NULL,
  profile VARCHAR(30) NULL,
  PRIMARY KEY(idDim_Device)
);

CREATE TABLE Dim_Location (
  idDim_Location INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  zip VARCHAR(30) NULL,
  longitude REAL NULL,
  latitude REAL NULL,
  city VARCHAR(30) NULL,
  state VARCHAR(30) NULL,
  uf VARCHAR(2) NULL,
  PRIMARY KEY(idDim_Location)
);

CREATE TABLE Dim_Media (
  idDim_Media INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  document VARCHAR(30) NULL,
  media VARCHAR(30) NULL,
  status_ VARCHAR(30) NULL,
  time TIME NULL,
  PRIMARY KEY(idDim_Media)
);

CREATE TABLE Dim_Program (
  idDim_Program INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  channel_code INTEGER UNSIGNED NULL,
  channel_name VARCHAR(30) NULL,
  name VARCHAR(100) NULL,
  age INTEGER UNSIGNED NULL,
  genre VARCHAR(4) NULL,
  subgenre VARCHAR(4) NULL,
  PRIMARY KEY(idDim_Program)
);

CREATE TABLE Dim_SocialNetwork (
  idDim_SocialNetwork INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NULL,
  PRIMARY KEY(idDim_SocialNetwork)
);

CREATE TABLE Dim_Time (
  idDim_Time INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  hour_12 INTEGER UNSIGNED NULL,
  hour_24 INTEGER UNSIGNED NULL,
  minute_ INTEGER UNSIGNED NULL,
  second_ INTEGER UNSIGNED NULL,
  PRIMARY KEY(idDim_Time)
);

CREATE TABLE Dim_User (
  idDim_User INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  age INTEGER UNSIGNED NULL,
  genre VARCHAR(1) NULL,
  PRIMARY KEY(idDim_User)
);

CREATE TABLE Dim_User_has_Dim_SocialNetwork (
  Dim_User_idDim_User INTEGER UNSIGNED NOT NULL,
  Dim_SocialNetwork_idDim_SocialNetwork INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Dim_User_idDim_User, Dim_SocialNetwork_idDim_SocialNetwork),
  INDEX Dim_User_has_Dim_SocialNetwork_FKIndex1(Dim_User_idDim_User),
  INDEX Dim_User_has_Dim_SocialNetwork_FKIndex2(Dim_SocialNetwork_idDim_SocialNetwork)
);

CREATE TABLE Iteraction (
  idIteraction INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  type_ VARCHAR(30) NULL,
  volume_level INTEGER UNSIGNED NULL,
  volume_mute BOOL NULL,
  key_code VARCHAR(30) NULL,
  key_action VARCHAR(30) NULL,
  PRIMARY KEY(idIteraction)
);

CREATE TABLE Iteraction_has_Dim_Data (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Data_idDim_Data INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Data_idDim_Data),
  INDEX Iteraction_has_Dim_Data_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Data_FKIndex2(Dim_Data_idDim_Data)
);

CREATE TABLE Iteraction_has_Dim_Device (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Device_idDim_Device INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Device_idDim_Device),
  INDEX Iteraction_has_Dim_Device_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Device_FKIndex2(Dim_Device_idDim_Device)
);

CREATE TABLE Iteraction_has_Dim_Location (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Location_idDim_Location INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Location_idDim_Location),
  INDEX Iteraction_has_Dim_Location_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Location_FKIndex2(Dim_Location_idDim_Location)
);

CREATE TABLE Iteraction_has_Dim_Media (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Media_idDim_Media INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Media_idDim_Media),
  INDEX Iteraction_has_Dim_Media_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Media_FKIndex2(Dim_Media_idDim_Media)
);

CREATE TABLE Iteraction_has_Dim_Program (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Program_idDim_Program INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Program_idDim_Program),
  INDEX Iteraction_has_Dim_Program_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Program_FKIndex2(Dim_Program_idDim_Program)
);

CREATE TABLE Iteraction_has_Dim_Time (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_Time_idDim_Time INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_Time_idDim_Time),
  INDEX Iteraction_has_Dim_Time_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_Time_FKIndex2(Dim_Time_idDim_Time)
);

CREATE TABLE Iteraction_has_Dim_User (
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  Dim_User_idDim_User INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Iteraction_idIteraction, Dim_User_idDim_User),
  INDEX Iteraction_has_Dim_User_FKIndex1(Iteraction_idIteraction),
  INDEX Iteraction_has_Dim_User_FKIndex2(Dim_User_idDim_User)
);

CREATE TABLE Resource_CPU (
  idResource_CPU INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Dim_Device_idDim_Device INTEGER UNSIGNED NOT NULL,
  model VARCHAR(30) NULL,
  clock VARCHAR(30) NULL,
  cores VARCHAR(30) NULL,
  arch VARCHAR(30) NULL,
  PRIMARY KEY(idResource_CPU),
  INDEX Resource_CPU_FKIndex1(Dim_Device_idDim_Device)
);

CREATE TABLE Resource_Hid (
  idResource_Hid INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Dim_Device_idDim_Device INTEGER UNSIGNED NOT NULL,
  model VARCHAR(30) NULL,
  type_ VARCHAR(30) NULL,
  PRIMARY KEY(idResource_Hid),
  INDEX Resource_Hid_FKIndex1(Dim_Device_idDim_Device)
);

CREATE TABLE Resource_Network (
  idResource_Network INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Dim_Device_idDim_Device INTEGER UNSIGNED NOT NULL,
  speed VARCHAR(30) NULL,
  model VARCHAR(300) NULL,
  PRIMARY KEY(idResource_Network),
  INDEX Resource_Network_FKIndex1(Dim_Device_idDim_Device)
);

CREATE TABLE Resource_Storage (
  idResource_Storage INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Dim_Device_idDim_Device INTEGER UNSIGNED NOT NULL,
  total VARCHAR(30) NULL,
  free VARCHAR(30) NULL,
  PRIMARY KEY(idResource_Storage),
  INDEX Resource_Storage_FKIndex1(Dim_Device_idDim_Device)
);


