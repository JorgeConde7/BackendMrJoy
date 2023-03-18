------ RESERVAS--------------

INSERT INTO `reservas` (`id_reserva`, `acompaniante`, `apellido`, `cant_personas`, `fecha_registro`, `fecha_reserva`, `flag_tipo_reserva`, `hora`, `id_login`, `id_paquete`, `nombres`, `telefono`, `total_pago`) VALUES
(1, 1, 'a', 22, '2022-07-12', '2022-08-01', '1', '10:00-11:00', 1, 1, 'bbb', '991855035', 1234),
(2, 1, 'a', 18, '2022-07-12', '2022-08-01', '1', '12:00-13:00', 1, 1, 'bbb', '991855035', 1234),
(3, 2, 'a', 16, '2022-07-12', '2022-08-01', '1', '16:00-17:00', 1, 1, 'bbb', '991855035', 1234),
(4, 3, 'a', 18, '2022-07-12', '2022-08-01', '1', '19:00-20:00', 1, 1, 'bbb', '991855035', 1234),
(5, 3, 'a', 21, '2022-07-12', '2022-08-02', '1', '11:00-12:00', 1, 1, 'bbb', '991855035', 1234),
(6, 2, 'a', 22, '2022-07-12', '2022-08-02', '1', '14:00-15:00', 1, 1, 'bbb', '991855035', 1234),
(7, 2, 'a', 25, '2022-07-12', '2022-08-02', '1', '18:00-19:00', 1, 1, 'bbb', '991855035', 1234),
(8, 1, 'a', 30, '2022-07-12', '2022-08-03', '1', '11:00-12:00', 1, 1, 'bbb', '991855035', 1234),
(9, 1, 'a', 18, '2022-07-12', '2022-08-03', '1', '16:30-17:30', 1, 1, 'bbb', '991855035', 1234),
(11, 1, 'Conde', 3, '2022-08-01', '2022-08-01', '0', '10:00', -1, 1, 'jorge', '1859123', 83.69999999999999);

--ENTRADAS ---
INSERT INTO tipo_entrada (id_tipo_entrada, descripcion, precio_unitario, ruta_img) VALUES
(1, 'Joy Star 90 Min.', 16, '/assets/images/entradas/entrada 3.png'),
(2, 'Joy Planet 90 Min.', 20, '/assets/images/entradas/entrada 2.png'),
(3, 'Visitantes Puruchuco', 6, '/assets/images/entradas/entrada 1.png');


--PAQUETES--------

INSERT INTO `paquetes` (`id_paquete`, `descripcion`, `precio`) VALUES
(1, 'Paquete Nito', 25.9),
(2, 'Paquete Mr. Joy', 32.9),
(3, 'Super Mr. Joy', 39.9);


-- PARQUES ---
INSERT INTO `parque` (`id_parque`, `horario1`, `horario2`, `nombre`, `ruta_img`) VALUES
(1, 'Lunes a Viernes 09:00 a 21:00', 'Sábado de 11:00 a 21:00 y Domingo de 11:00 a 19:00', 'Mr Joy Lima', NULL),
(2, 'Lunes a Viernes 09:00 a 21:00', 'Sábado de 11:00 a 21:00 y Domingo de 11:00 a 19:00', 'Joy Trujillo', NULL),
(3, 'Lunes a Viernes 09:00 a 21:00', 'Sábado de 11:00 a 21:00 y Domingo de 11:00 a 19:00', 'Mr Joy Piura', NULL);

-- PROMOCIONES --
INSERT INTO prueba.promociones (id_promociones, descripcion, foto, titulo) VALUES (NULL, 'Al llegar a la boleteria reclama tu 20% de descuento solo de Lunes a Viernes antes de las 6:30 pm.', NULL, 'Promo 1'), (NULL, 'Si eres cliente frecuentemente en nuestro sitio reclama tu pase VIP.', NULL, 'Promo 2');
INSERT INTO prueba.promociones (id_promociones, descripcion, foto, titulo) VALUES (NULL, 'Aprovecha que los DOMINGOS son los unicos dias para disfrutar de los juegos Libre para toda la familia.', NULL, 'Promo 4'), (NULL, 'Recuerda que si eres socio en Mr.Joy podras disfrutar de todos los descuentos.', NULL, 'Promo 5');