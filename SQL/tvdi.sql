drop database tvdi;
create database tvdi;
use tvdi;
CREATE TABLE Channel (
  idChannel INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Code INTEGER UNSIGNED NULL,
  Name VARCHAR(30) NULL,
  PRIMARY KEY(idChannel)
);

CREATE TABLE Context (
  idContext INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Context_idContext INTEGER UNSIGNED NULL,
  document_iddocument INTEGER UNSIGNED NULL,
  id VARCHAR(30) NULL,
  PRIMARY KEY(idContext),
  INDEX Context_FKIndex2(document_iddocument),
  INDEX Context_FKIndex3(Context_idContext)
);

CREATE TABLE Country (
  idCountry INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Name VARCHAR(30) NULL,
  PRIMARY KEY(idCountry)
);

CREATE TABLE Device (
  idDevice INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  STB_Device_idSTB_Device INTEGER UNSIGNED NOT NULL,
  serialNumber VARCHAR(30) NULL,
  Type_ VARCHAR(30) NULL,
  Profile VARCHAR(30) NULL,
  PRIMARY KEY(idDevice),
  INDEX Device_FKIndex1(STB_Device_idSTB_Device)
);

CREATE TABLE document (
  iddocument INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nclStateMachine_idnclStateMachine INTEGER UNSIGNED NOT NULL,
  id VARCHAR(30) NULL,
  PRIMARY KEY(iddocument),
  INDEX document_FKIndex1(nclStateMachine_idnclStateMachine)
);

CREATE TABLE Head (
  idHead INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  User__idUser INTEGER UNSIGNED NOT NULL,
  Location_idLocation INTEGER UNSIGNED NOT NULL,
  Watch_TV_idWatch_TV INTEGER UNSIGNED NOT NULL,
  STB_Device_idSTB_Device INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idHead),
  INDEX Head_FKIndex1(STB_Device_idSTB_Device),
  INDEX Head_FKIndex2(Watch_TV_idWatch_TV),
  INDEX Head_FKIndex3(Location_idLocation),
  INDEX Head_FKIndex4(User__idUser)
);

CREATE TABLE Information (
  idInformation INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Resource_idResource INTEGER UNSIGNED NOT NULL,
  Name VARCHAR(30) NULL,
  Value VARCHAR(150) NULL,
  PRIMARY KEY(idInformation),
  INDEX Information_FKIndex1(Resource_idResource)
);

CREATE TABLE Interface (
  idInterface INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Media_idMedia INTEGER UNSIGNED NOT NULL,
  id VARCHAR(30) NULL,
  Status_ VARCHAR(30) NULL,
  PRIMARY KEY(idInterface),
  INDEX Interface_FKIndex1(Media_idMedia)
);

CREATE TABLE Iteraction (
  idIteraction INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Program_idProgram INTEGER UNSIGNED NOT NULL,
  Volume_idVolume INTEGER UNSIGNED NOT NULL,
  Key__idKey_ INTEGER UNSIGNED NOT NULL,
  Watch_TV_idWatch_TV INTEGER UNSIGNED NOT NULL,
  Time DATETIME NULL,
  Type_ VARCHAR(30) NULL,
  PRIMARY KEY(idIteraction),
  INDEX Iteraction_FKIndex1(Watch_TV_idWatch_TV),
  INDEX Iteraction_FKIndex2(Key__idKey_),
  INDEX Iteraction_FKIndex3(Volume_idVolume),
  INDEX Iteraction_FKIndex4(Program_idProgram)
);

CREATE TABLE Key_ (
  idKey_ INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Code VARCHAR(30) NULL,
  Action VARCHAR(30) NULL,
  PRIMARY KEY(idKey_)
);

CREATE TABLE Location (
  idLocation INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Zip INTEGER UNSIGNED NULL,
  Latitude REAL NULL,
  Longitude REAL NULL,
  PRIMARY KEY(idLocation)
);

CREATE TABLE Log_ (
  idLog_ INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Watch_TV_idWatch_TV INTEGER UNSIGNED NOT NULL,
  Log_Date TIMESTAMP NULL,
  ip VARCHAR(15) NULL,
  Size REAL NULL,
  PRIMARY KEY(idLog_),
  INDEX Upload_FKIndex1(Watch_TV_idWatch_TV)
);

CREATE TABLE Media (
  idMedia INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Context_idContext INTEGER UNSIGNED NULL,
  document_iddocument INTEGER UNSIGNED NULL,
  id VARCHAR(30) NULL,
  Status_ VARCHAR(30) NULL,
  Time TIME NULL,
  PRIMARY KEY(idMedia),
  INDEX Media_FKIndex1(document_iddocument),
  INDEX Media_FKIndex2(Context_idContext)
);

CREATE TABLE Meta (
  idMeta INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Program_idProgram INTEGER UNSIGNED NOT NULL,
  meta VARCHAR(300) NULL,
  PRIMARY KEY(idMeta),
  INDEX Meta_FKIndex1(Program_idProgram)
);

CREATE TABLE nclStateMachine (
  idnclStateMachine INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Iteraction_idIteraction INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idnclStateMachine),
  INDEX nclStateMachine_FKIndex1(Iteraction_idIteraction)
);

CREATE TABLE Program (
  idProgram INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Channel_idChannel INTEGER UNSIGNED NOT NULL,
  Age INTEGER UNSIGNED NULL,
  Genre VARCHAR(4) NULL,
  Code INTEGER UNSIGNED NULL,
  Name VARCHAR(100) NULL,
  SubGenre VARCHAR(4) NULL,
  PRIMARY KEY(idProgram),
  INDEX Program_FKIndex1(Channel_idChannel)
);

CREATE TABLE Property (
  idProperty INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Media_idMedia INTEGER UNSIGNED NOT NULL,
  name VARCHAR(30) NULL,
  value VARCHAR(30) NULL,
  PRIMARY KEY(idProperty),
  INDEX Property_FKIndex1(Media_idMedia)
);

CREATE TABLE Resource (
  idResource INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Device_idDevice INTEGER UNSIGNED NULL,
  STB_Device_idSTB_Device INTEGER UNSIGNED NULL,
  Type_ VARCHAR(30) NULL,
  PRIMARY KEY(idResource),
  INDEX Resource_FKIndex1(STB_Device_idSTB_Device),
  INDEX Resource_FKIndex2(Device_idDevice)
);

CREATE TABLE SocialNetwork (
  idSocialNetwork INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  User__idUser INTEGER UNSIGNED NOT NULL,
  SocialNetWorkName_idSocialNetWorkName INTEGER UNSIGNED NOT NULL,
  identification VARCHAR(50) NULL,
  PRIMARY KEY(idSocialNetwork),
  INDEX SocialNetwork_FKIndex1(SocialNetWorkName_idSocialNetWorkName),
  INDEX SocialNetwork_FKIndex2(User__idUser)
);

CREATE TABLE SocialNetWorkName (
  idSocialNetWorkName INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Name VARCHAR(30) NULL,
  PRIMARY KEY(idSocialNetWorkName)
);

CREATE TABLE STB_Device (
  idSTB_Device INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Profile VARCHAR(30) NULL,
  Type_ VARCHAR(30) NULL,
  SerialNumber VARCHAR(30) NULL,
  PRIMARY KEY(idSTB_Device)
);

CREATE TABLE User_ (
  idUser INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Genre CHAR(1) NULL,
  Birth DATE NULL,
  Identification VARCHAR(50) NULL,
  PRIMARY KEY(idUser)
);

CREATE TABLE Volume (
  idVolume INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Level INTEGER UNSIGNED NULL,
  Mute BOOL NULL,
  PRIMARY KEY(idVolume)
);

CREATE TABLE Watch_TV (
  idWatch_TV INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Country_idCountry INTEGER UNSIGNED NOT NULL,
  StartDate DATETIME NULL,
  EndDate DATETIME NULL,
  FlagETL BOOL NULL,
  PRIMARY KEY(idWatch_TV),
  INDEX Watch_TV_FKIndex1(Country_idCountry)
);


