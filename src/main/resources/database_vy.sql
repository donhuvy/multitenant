-- Database: test

-- DROP DATABASE IF EXISTS test;

CREATE DATABASE test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Vietnamese_Vietnam.1258'
    LC_CTYPE = 'Vietnamese_Vietnam.1258'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
--------------------------------------------------------------------------------
-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;
--------------------------------------------------------------------------------
-- Table: public.datasourceconfig

-- DROP TABLE IF EXISTS public.datasourceconfig;

CREATE TABLE IF NOT EXISTS public.datasourceconfig
(
    id bigint NOT NULL,
    driverclassname character varying(255) COLLATE pg_catalog."default",
    url character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    initialize boolean,
    CONSTRAINT datasourceconfig_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.datasourceconfig
    OWNER to postgres;

--------------------------------------------------------------------------------
-- SCHEMA: test1

-- DROP SCHEMA IF EXISTS test1 ;

CREATE SCHEMA IF NOT EXISTS test1
    AUTHORIZATION postgres;
--------------------------------------------------------------------------------
-- Table: test1.city

-- DROP TABLE IF EXISTS test1.city;

CREATE TABLE IF NOT EXISTS test1.city
(
    id bigint,
    name character varying(200) COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS test1.city
    OWNER to postgres;
--------------------------------------------------------------------------------
-- SCHEMA: test2

-- DROP SCHEMA IF EXISTS test2 ;

CREATE SCHEMA IF NOT EXISTS test2
    AUTHORIZATION postgres;
--------------------------------------------------------------------------------
-- Table: test2.city

-- DROP TABLE IF EXISTS test2.city;

CREATE TABLE IF NOT EXISTS test2.city
(
    id bigint,
    name character varying(200) COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS test2.city
    OWNER to postgres;
--------------------------------------------------------------------------------
