--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: teste_backup; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA teste_backup;


ALTER SCHEMA teste_backup OWNER TO admin;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: client; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    payment_plan_id bigint,
    cnpj character varying(255),
    company_name character varying(255),
    cpf character varying(255),
    email character varying(255),
    name character varying(255),
    phone character varying(255)
);


ALTER TABLE public.client OWNER TO admin;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO admin;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: message; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.message (
    client_id bigint,
    id bigint NOT NULL,
    message_type character varying(255),
    receiver_phone character varying(255),
    sender_phone character varying(255),
    text_message character varying(255)
);


ALTER TABLE public.message OWNER TO admin;

--
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_id_seq OWNER TO admin;

--
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- Name: payment_plan; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.payment_plan (
    base_plan_balance double precision NOT NULL,
    base_plan_limit double precision NOT NULL,
    id bigint NOT NULL,
    plan_type character varying(31) NOT NULL
);


ALTER TABLE public.payment_plan OWNER TO admin;

--
-- Name: payment_plan_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.payment_plan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_plan_id_seq OWNER TO admin;

--
-- Name: payment_plan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.payment_plan_id_seq OWNED BY public.payment_plan.id;


--
-- Name: post_paid_plan; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.post_paid_plan (
    id bigint NOT NULL
);


ALTER TABLE public.post_paid_plan OWNER TO admin;

--
-- Name: pre_paid_plan; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.pre_paid_plan (
    id bigint NOT NULL
);


ALTER TABLE public.pre_paid_plan OWNER TO admin;

--
-- Name: user_phone_numbers; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.user_phone_numbers (
    id bigint NOT NULL,
    user_phone_number character varying(255)
);


ALTER TABLE public.user_phone_numbers OWNER TO admin;

--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: message id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- Name: payment_plan id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.payment_plan ALTER COLUMN id SET DEFAULT nextval('public.payment_plan_id_seq'::regclass);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.client (id, payment_plan_id, cnpj, company_name, cpf, email, name, phone) FROM stdin;
1	1	15.436.940/0001-03	Irrah	247.794.330-88	carlos@email.com	Carlos	(44)99234-5678
2	2	15.436.940/0001-03	Irrah	247.794.330-88	carlos@email.com	Carlos Alberto	(44)99234-5678
4	3	15.436.940/0001-03	Irrah	247.794.330-88	carlos@email.com	Carlos Alberto	(44)99234-5678
5	3	15.436.940/0001-03	Irrah	247.794.330-88	carlos@email.com	Carlos Alberto	(44)99234-5678
3	3	15.436.940/0001-03	Irrah	247.794.330-88	narcizo@email.com	Narcizo	(44)99143-1516
6	4	15.436.940/0001-03	Irrah	247.794.330-88	carlos@email.com	Carlos Alberto	(44)99234-5678
\.


--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.message (client_id, id, message_type, receiver_phone, sender_phone, text_message) FROM stdin;
1	42	sms	(44)99143-1516	(44)99234-5678	Teste Twillio
1	44	whatsapp	(44)99143-1516	(44)99234-5678	Teste twillio zap
1	45	whatsapp	(44)99143-1516	(44)99234-5678	Mandando essa mensagem pelo Whats atrav├®s do API do twilio. Teste-Irrah
1	46	sms	(44)99143-1516	(44)99234-5678	Mandando essa mensagem pelo sms atrav├®s da API do twilio. Teste-Irrah
3	47	sms	(44)99143-1516	(44)99143-1516	Mandando essa mensagem pelo sms atrav├®s da API do twilio. Teste-Irrah
3	48	whatsapp	(44)99143-1516	(44)99143-1516	Mandando essa mensagem pelo sms atrav├®s da API do twilio. Teste-Irrah
3	49	whatsapp	(44)99143-1516	(44)99143-1516	teste twilio filnal
3	50	whatsapp	(44)99143-1516	(44)99143-1516	teste twilio filnal
3	51	whatsapp	(44)99143-1516	(44)99143-1516	teste twilio filnal
3	52	sms	(44)99143-1516	(44)99143-1516	teste twilio filnal
3	53	whatsapp	(44)99143-1516	(44)99143-1516	teste twilio filnal
3	54	whatsapp	(44)99143-1516	(44)99143-1516	teste twilio filnal
1	55	sms	(44)98888-7777	(44)99234-5678	oiiiii
1	56	sms	(44)96333-4444	(44)99234-5678	oiiiii
1	57	sms	(44)98888-7777	(44)99234-5678	oiiiii
1	58	sms	(44)96333-4444	(44)99234-5678	oiiiii
6	59	whatsapp	(44)99143-1516	(44)99234-5678	teste twilio filnal
6	60	sms	(44)99143-1516	(44)99234-5678	teste twilio filnal
6	61	sms	(44)99143-1516	(44)99234-5678	teste twilio filnal
6	62	sms	(44)99143-1516	(44)99234-5678	teste twilio filnal
6	63	sms	(44)99143-1516	(44)99234-5678	teste twilio filnal
\N	64	\N	\N	\N	\N
\.


--
-- Data for Name: payment_plan; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.payment_plan (base_plan_balance, base_plan_limit, id, plan_type) FROM stdin;
51	100	2	POSTPAID
7	0	3	PREPAID
197.25	0	1	PREPAID
1	1	4	POSTPAID
\.


--
-- Data for Name: post_paid_plan; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.post_paid_plan (id) FROM stdin;
2
4
\.


--
-- Data for Name: pre_paid_plan; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.pre_paid_plan (id) FROM stdin;
1
3
\.


--
-- Data for Name: user_phone_numbers; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.user_phone_numbers (id, user_phone_number) FROM stdin;
1	(44)98888-7777
1	(44)96333-4444
2	(44)98888-7777
2	(44)96333-4444
4	(44)98888-7777
4	(44)96333-4444
3	(44)99143-1516
\.


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.client_id_seq', 6, true);


--
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.message_id_seq', 64, true);


--
-- Name: payment_plan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.payment_plan_id_seq', 4, true);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: payment_plan payment_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.payment_plan
    ADD CONSTRAINT payment_plan_pkey PRIMARY KEY (id);


--
-- Name: post_paid_plan post_paid_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.post_paid_plan
    ADD CONSTRAINT post_paid_plan_pkey PRIMARY KEY (id);


--
-- Name: pre_paid_plan pre_paid_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.pre_paid_plan
    ADD CONSTRAINT pre_paid_plan_pkey PRIMARY KEY (id);


--
-- Name: post_paid_plan fk2rbjnnnwuqerlu4j24kg0tu0g; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.post_paid_plan
    ADD CONSTRAINT fk2rbjnnnwuqerlu4j24kg0tu0g FOREIGN KEY (id) REFERENCES public.payment_plan(id);


--
-- Name: pre_paid_plan fk9icf89mlgobvdmca7wb27v7fj; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.pre_paid_plan
    ADD CONSTRAINT fk9icf89mlgobvdmca7wb27v7fj FOREIGN KEY (id) REFERENCES public.payment_plan(id);


--
-- Name: user_phone_numbers fkdlgwl1k0fu0elkun4dnx05egx; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.user_phone_numbers
    ADD CONSTRAINT fkdlgwl1k0fu0elkun4dnx05egx FOREIGN KEY (id) REFERENCES public.client(id);


--
-- Name: message fkgvo2vw6xgtws6ognjlnrxyi5x; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkgvo2vw6xgtws6ognjlnrxyi5x FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client fkjrfcjkulcmb9dml1mg2ul2qj9; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fkjrfcjkulcmb9dml1mg2ul2qj9 FOREIGN KEY (payment_plan_id) REFERENCES public.payment_plan(id);


--
-- PostgreSQL database dump complete
--

