PGDMP                         {            bdlattes    15.2    15.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16398    bdlattes    DATABASE        CREATE DATABASE bdlattes WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE bdlattes;
                postgres    false            ?            1259    17227 	   instituto    TABLE     ?   CREATE TABLE public.instituto (
    id bigint NOT NULL,
    acronimo character varying(255),
    nome character varying(255)
);
    DROP TABLE public.instituto;
       public         heap    postgres    false            ?            1259    17226    instituto_id_seq    SEQUENCE     y   CREATE SEQUENCE public.instituto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.instituto_id_seq;
       public          postgres    false    215            ?           0    0    instituto_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.instituto_id_seq OWNED BY public.instituto.id;
          public          postgres    false    214            e           2604    17230    instituto id    DEFAULT     l   ALTER TABLE ONLY public.instituto ALTER COLUMN id SET DEFAULT nextval('public.instituto_id_seq'::regclass);
 ;   ALTER TABLE public.instituto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            ?          0    17227 	   instituto 
   TABLE DATA           7   COPY public.instituto (id, acronimo, nome) FROM stdin;
    public          postgres    false    215   ?
       ?           0    0    instituto_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.instituto_id_seq', 2, true);
          public          postgres    false    214            g           2606    17234    instituto instituto_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.instituto
    ADD CONSTRAINT instituto_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.instituto DROP CONSTRAINT instituto_pkey;
       public            postgres    false    215            ?   :   x?3?u????,K-*?LILIUpKMI-J?QH?W??W ?x%??f?s??qqq ??     