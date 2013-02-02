SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
CREATE SCHEMA IF NOT EXISTS `empleabilidad` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `mydb` ;
USE `empleabilidad` ;

-- -----------------------------------------------------
-- Table `empleabilidad`.`categoria_de_profesiones`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`categoria_de_profesiones` (
  `idcategoria` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre_categoria` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`idcategoria`) ,
  UNIQUE INDEX `idcategoria_UNIQUE` (`idcategoria` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`profesion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`profesion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `categoria` INT(10) UNSIGNED NOT NULL ,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `Profesion_categoriaid_fk_idx` (`categoria` ASC) ,
  CONSTRAINT `Profesion_categoriaid_fk`
    FOREIGN KEY (`categoria` )
    REFERENCES `empleabilidad`.`categoria_de_profesiones` (`idcategoria` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = 'Encarga de almacenar la información de las distintas profesiones formales e informales';


-- -----------------------------------------------------
-- Table `empleabilidad`.`area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`area` (
  `idarea` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `profesion` INT(10) UNSIGNED NOT NULL ,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `categoria` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`idarea`) ,
  UNIQUE INDEX `idarea_UNIQUE` (`idarea` ASC) ,
  INDEX `profesion_idx` (`profesion` ASC) ,
  CONSTRAINT `profesion`
    FOREIGN KEY (`profesion` )
    REFERENCES `empleabilidad`.`profesion` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`capacidad`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`capacidad` (
  `idcapacidad` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `area` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idcapacidad`) ,
  UNIQUE INDEX `idcapacidad_UNIQUE` (`idcapacidad` ASC) ,
  INDEX `Capacidad_Areaid_fk_idx` (`area` ASC) ,
  CONSTRAINT `Capacidad_Areaid_fk`
    FOREIGN KEY (`area` )
    REFERENCES `empleabilidad`.`area` (`idarea` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`datos_personales`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`datos_personales` (
  `id` INT(10) UNSIGNED NOT NULL ,
  `nombres` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `apellido1` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `sexo` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `estado_civil` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `lugar_nacimiento` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `fecha_nacimiento` DATE NOT NULL ,
  `direccion` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `barrio` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `cudad` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `telefono` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `celular` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `descripcion` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `tipo_documento` INT(1) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`estudios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`estudios` (
  `idestudios` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `aspirante` INT(10) UNSIGNED NOT NULL ,
  `titulo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'En caso de educación no formal, este campo registra el nombre del curso, seminariio, conferencia; en el caso de certificaciones tecnológica, aqui se guarda el nombre de la certificación' ,
  `institucion` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'Quien concede el título, curso, seminario, certificación, etcétera.' ,
  `ciudad` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'La ciudad donde se culminó formalmente el estudio, o donde se hizo el curso, seminario, taller, certificación, etcétera.' ,
  `fecha_titulacion` DATE NOT NULL COMMENT 'Fecha en que se logra el titulo o se culminó el curso, seminario, taller, conferencia, etc.' ,
  `nivel_estudio` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'Primaria, Bachillerato, Técnico, tecnólogo, profesional, especialización, maestría, curso, seminario, certificación, ponencia, conferencia, etcetera.' ,
  `estado_estudio` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'Terminado, en curso, aplazado.' ,
  `area_estudio` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL COMMENT 'Creo que corresponde a Segurida, ingenniería, laboral, etcétera.' ,
  `fecha_start` DATE NOT NULL COMMENT 'Fecha de inicio de los estudios.' ,
  `tipo_estudio` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `promedio` DECIMAL(5,1) NULL DEFAULT NULL ,
  `materias_carrera` INT(11) NULL DEFAULT NULL ,
  `materias_cursadas` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idestudios`) ,
  UNIQUE INDEX `idestudios_UNIQUE` (`idestudios` ASC) ,
  INDEX `aspirante_idx` (`aspirante` ASC) ,
  CONSTRAINT `Estudio_Aspiranteid_fk`
    FOREIGN KEY (`aspirante` )
    REFERENCES `empleabilidad`.`datos_personales` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = 'Esta clase tiene como propósito almacenar los datos relacionados con los estudios realizados por un aspirante. Un aspirante puede tener varios estudios de interés: primaria, bachillerato, técnico, tecnólogo, profesional, especialización, maestría, doctorado, curso, taller, seminario, conferencia, etcétera. El tipo de estudio se clasifican en formal e informal.\n\nEsta clase debe contener el id del aspirante y los datos de sus estudios, los cuales son, al menos, los siguientes:\nNombre de la institución.\nAño de inicio\nAño de terminación.\nTitulo obtenido\nCiudad de la Institución otorgadora del titulo\n\nImportante, tratar de obtener lista de colegios por ciudades, de ciudades por departamentos, de universidades por ciudades,etcétera. Algo parecido a lo que se hace en el portal de empleo del sena';


-- -----------------------------------------------------
-- Table `empleabilidad`.`capacidades_por_estudios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`capacidades_por_estudios` (
  `idcapacidad_por_estudio` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `capacidad` INT(10) UNSIGNED NOT NULL ,
  `estudios` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idcapacidad_por_estudio`) ,
  UNIQUE INDEX `idcapacidad_por_estudio_UNIQUE` (`idcapacidad_por_estudio` ASC) ,
  INDEX `capacidad_idx` (`capacidad` ASC) ,
  INDEX `estudios_idx` (`estudios` ASC) ,
  CONSTRAINT `CapacidadporEstudio_Capacidadid_fk`
    FOREIGN KEY (`capacidad` )
    REFERENCES `empleabilidad`.`capacidad` (`idcapacidad` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `CapacidadporEstudio_Estudiosid_fk`
    FOREIGN KEY (`estudios` )
    REFERENCES `empleabilidad`.`estudios` (`idestudios` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`experiencia_laboral`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`experiencia_laboral` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `area` INT(10) UNSIGNED NOT NULL ,
  `aspirante` INT(10) UNSIGNED NOT NULL ,
  `empresa` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `cargo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `fecha_in` DATE NOT NULL ,
  `fecha_out` DATE NOT NULL ,
  `actividad_empresa` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `tipo_cargo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `descripcion` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `area_idx` (`area` ASC) ,
  INDEX `aspirante_idx` (`aspirante` ASC) ,
  CONSTRAINT `Experiencia_Areaid_fk`
    FOREIGN KEY (`area` )
    REFERENCES `empleabilidad`.`area` (`idarea` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `Experiencia_Aspiranteid_fk`
    FOREIGN KEY (`aspirante` )
    REFERENCES `empleabilidad`.`datos_personales` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`capacidades_por_experiencias`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`capacidades_por_experiencias` (
  `idcapporexperiencia` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `capacidad` INT(10) UNSIGNED NOT NULL ,
  `experiencia` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idcapporexperiencia`) ,
  UNIQUE INDEX `idcapporexperiencia_UNIQUE` (`idcapporexperiencia` ASC) ,
  INDEX `capacidad_idx` (`capacidad` ASC) ,
  INDEX `experiencia_idx` (`experiencia` ASC) ,
  CONSTRAINT `CapacidadporExperiencia_Capacidadid_fk`
    FOREIGN KEY (`capacidad` )
    REFERENCES `empleabilidad`.`capacidad` (`idcapacidad` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `CapacidadporExperiencia_Experienciaid_fk`
    FOREIGN KEY (`experiencia` )
    REFERENCES `empleabilidad`.`experiencia_laboral` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`empresas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`empresas` (
  `nit` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `actividad` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `nombre_contacto` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `representante_legal` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `celular_contacto` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `telefono_contacto` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `direccion` VARCHAR(128) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `descripcion` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`nit`) ,
  UNIQUE INDEX `idempresas_UNIQUE` (`nit` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`ofertas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`ofertas` (
  `idofertas` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nit` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `fecha_start` DATE NOT NULL ,
  `fecha_end` DATE NOT NULL ,
  `cargo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `salario` DECIMAL(10,0) NOT NULL ,
  `descripcion` VARCHAR(300) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `nivel_estudios_min` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `nivel_estudios_max` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `anios_experiencia` INT(2) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idofertas`) ,
  UNIQUE INDEX `idofertas_UNIQUE` (`idofertas` ASC) ,
  INDEX `empresa_idx` (`nit` ASC) ,
  CONSTRAINT `Ofertas_Empresaid_fk`
    FOREIGN KEY (`nit` )
    REFERENCES `empleabilidad`.`empresas` (`nit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`capacidades_por_ofertas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`capacidades_por_ofertas` (
  `idcapporofertas` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `oferta` INT(10) UNSIGNED NOT NULL ,
  `capacidad` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idcapporofertas`) ,
  UNIQUE INDEX `idcapporofertas_UNIQUE` (`idcapporofertas` ASC) ,
  INDEX `oferta_idx` (`oferta` ASC) ,
  INDEX `capacidad_idx` (`capacidad` ASC) ,
  CONSTRAINT `CapacidadesporOfertas_Capacidadid_fk`
    FOREIGN KEY (`capacidad` )
    REFERENCES `empleabilidad`.`capacidad` (`idcapacidad` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `CapacidadesporOfertas_Ofertaid_fk`
    FOREIGN KEY (`oferta` )
    REFERENCES `empleabilidad`.`ofertas` (`idofertas` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`inscripcion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`inscripcion` (
  `idinscripcion` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `aspirante` INT(10) UNSIGNED NOT NULL ,
  `oferta` INT(10) UNSIGNED NOT NULL ,
  `fecha` DATE NOT NULL ,
  `entrevista` DATE NOT NULL ,
  PRIMARY KEY (`idinscripcion`) ,
  UNIQUE INDEX `idinscripcion_UNIQUE` (`idinscripcion` ASC) ,
  INDEX `aspirante_idx` (`aspirante` ASC) ,
  INDEX `oferta_idx` (`oferta` ASC) ,
  CONSTRAINT `Inscripcion_Aspiranteid_fk`
    FOREIGN KEY (`aspirante` )
    REFERENCES `empleabilidad`.`datos_personales` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `Inscripcion_Ofertaid_fk`
    FOREIGN KEY (`oferta` )
    REFERENCES `empleabilidad`.`ofertas` (`idofertas` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`pruebas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`pruebas` (
  `idpruebas` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre_prueba` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`idpruebas`) ,
  UNIQUE INDEX `idpruebas_UNIQUE` (`idpruebas` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`evaluaciones`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`evaluaciones` (
  `idtest` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `inscripcion` INT(10) UNSIGNED NOT NULL ,
  `prueba` INT(10) UNSIGNED NOT NULL ,
  `puntuacion` DECIMAL(4,1) NOT NULL ,
  `cualificacion` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `comentarios` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`idtest`) ,
  UNIQUE INDEX `identrevistas_UNIQUE` (`idtest` ASC) ,
  INDEX `inscripcion_idx` (`inscripcion` ASC) ,
  INDEX `prueba_idx` (`prueba` ASC) ,
  CONSTRAINT `Evaluaciones_Inscripcionid_fk`
    FOREIGN KEY (`inscripcion` )
    REFERENCES `empleabilidad`.`inscripcion` (`idinscripcion` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `Evaluaciones_Pruebaid_fk`
    FOREIGN KEY (`prueba` )
    REFERENCES `empleabilidad`.`pruebas` (`idpruebas` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`profesiones_por_aspirante`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`profesiones_por_aspirante` (
  `idprofesones_aspirante` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `profesion` INT(10) UNSIGNED NOT NULL ,
  `aspirante` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`idprofesones_aspirante`) ,
  UNIQUE INDEX `idprofesones_aspirante_UNIQUE` (`idprofesones_aspirante` ASC) ,
  INDEX `profesion_idx` (`profesion` ASC) ,
  INDEX `aspirante_idx` (`aspirante` ASC) ,
  CONSTRAINT `Profesionporaspirante_Aspiranteid_fk`
    FOREIGN KEY (`aspirante` )
    REFERENCES `empleabilidad`.`datos_personales` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `Profesionporaspirante_Profesionid_fk`
    FOREIGN KEY (`profesion` )
    REFERENCES `empleabilidad`.`profesion` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`profesiones_por_oferta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`profesiones_por_oferta` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `oferta` INT(10) UNSIGNED NOT NULL ,
  `profesion` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `Profesionporoferta_oferta_idx` (`oferta` ASC) ,
  INDEX `Profesionporoferta_profesion_fk_idx` (`profesion` ASC) ,
  CONSTRAINT `Profesionporoferta_oferta_fk`
    FOREIGN KEY (`oferta` )
    REFERENCES `empleabilidad`.`ofertas` (`idofertas` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `Profesionporoferta_profesion_fk`
    FOREIGN KEY (`profesion` )
    REFERENCES `empleabilidad`.`profesion` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `empleabilidad`.`referencias`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `empleabilidad`.`referencias` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `aspirante` INT(10) UNSIGNED NOT NULL ,
  `nombre` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `telefono` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `celular` VARCHAR(13) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(128) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `direccion` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `barrio` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `ciudad` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `profesion` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `cargo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `tipo` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `aspirante_idx` (`aspirante` ASC) ,
  CONSTRAINT `aspirante`
    FOREIGN KEY (`aspirante` )
    REFERENCES `empleabilidad`.`datos_personales` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
