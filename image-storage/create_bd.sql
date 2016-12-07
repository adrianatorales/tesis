-- Database: tesisdt

-- DROP DATABASE tesisdt;

CREATE DATABASE tesisdt
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;

﻿-- Table: rgb_image

-- DROP TABLE rgb_image;

CREATE TABLE rgb_image
(
  id_rgb_image serial NOT NULL,
  channel_red bytea,
  channel_green bytea,
  channel_blue bytea,
  width integer,
  height integer,
  extension character varying(5),
  noise_name character varying(200),
  noise_probability double precision,
  description double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rgb_image
  OWNER TO postgres;

-- Table: rgb_image_data_channel_blue

-- DROP TABLE rgb_image_data_channel_blue;

CREATE TABLE rgb_image_data_channel_blue
(
  id_rgb_image_data_channel_blue serial NOT NULL,
  id_rgb_image integer NOT NULL,
  mean double precision,
  std_dev double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rgb_image_data_channel_blue
  OWNER TO postgres;


-- Table: rgb_image_data_channel_green

-- DROP TABLE rgb_image_data_channel_green;

CREATE TABLE rgb_image_data_channel_green
(
  id_rgb_image_data_channel_green integer NOT NULL,
  id_rgb_image integer NOT NULL,
  mean double precision,
  std_dev double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rgb_image_data_channel_green
  OWNER TO postgres;

-- Table: rgb_image_data_channel_red

-- DROP TABLE rgb_image_data_channel_red;

CREATE TABLE rgb_image_data_channel_red
(
  id_rgb_image_data_channel_red serial NOT NULL,
  id_rgb_image integer NOT NULL,
  mean double precision,
  std_dev double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rgb_image_data_channel_red
  OWNER TO postgres;


-- Table: rgb_window

-- DROP TABLE rgb_window;

CREATE TABLE rgb_window
(
  id_window integer NOT NULL,
  id_rgb_image integer,
  x integer,
  y integer,
  height integer,
  width integer,
  pixel_count integer,
  window_count integer,
  window_number integer,
  channel integer,
  mean double precision,
  variance double precision,
  modes double precision,
  minimum double precision,
  maximum double precision,
  energy double precision,
  entropy double precision,
  skewness double precision,
  kurtosis double precision,
  third_central_moment double precision,
  fourth_central_moment double precision,
  fifth_central_moment double precision,
  sixth_central_moment double precision,
  seventh_central_moment double precision,
  eighth_central_moment double precision,
  smoothness double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rgb_window
  OWNER TO postgres;


-- Table: sequence

-- DROP TABLE sequence;

CREATE TABLE sequence
(
  seq_name character varying(50) NOT NULL,
  seq_count numeric(38,0),
  CONSTRAINT pk_sequence PRIMARY KEY (seq_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sequence
  OWNER TO postgres;



-- Table: resultados

-- DROP TABLE resultados;

CREATE TABLE resultados
(
  numero_imagen double precision NOT NULL,
  probabilidad double precision NOT NULL,
  nombre_metodo character varying(34) NOT NULL,
  "compReducido" double precision,
  comp1 character varying(20),
  comp2 character varying(20),
  comp3 character varying(22),
  combinacion character varying(4) NOT NULL,
  dimension_es double precision NOT NULL,
  ventanas double precision NOT NULL,
  "refHue" double precision NOT NULL,
  ruido character varying(9) NOT NULL,
  "maeEuclidean" double precision,
  ncd double precision,
  "metricOfSimilarityM1" double precision,
  "metricOfSimilarityM2" double precision,
  mae_marginal double precision,
  CONSTRAINT pk_resultados PRIMARY KEY (numero_imagen, probabilidad, nombre_metodo, combinacion, dimension_es, ventanas, "refHue", ruido)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE resultados
  OWNER TO postgres;



-- Table: resultados_alpha_modulus_lex

-- DROP TABLE resultados_alpha_modulus_lex;

CREATE TABLE resultados_alpha_modulus_lex
(
  numero_imagen double precision,
  probabilidad double precision,
  nombre_metodo character varying(34),
  "compReducido" double precision,
  comp1 character varying(20),
  comp2 character varying(20),
  comp3 character varying(22),
  combinacion character varying(4),
  dimension_es double precision,
  ventanas double precision,
  "refHue" double precision,
  ruido character varying(9),
  "maeEuclidean" double precision,
  ncd double precision,
  "metricOfSimilarityM1" double precision,
  "metricOfSimilarityM2" double precision,
  mae_marginal double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE resultados_alpha_modulus_lex
  OWNER TO postgres;

insert INTO sequence values ('SEQ_GEN', 0);