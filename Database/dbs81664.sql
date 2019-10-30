-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Servidor: db5000086936.hosting-data.io
-- Tiempo de generación: 08-06-2019 a las 22:01:09
-- Versión del servidor: 5.7.25-log
-- Versión de PHP: 7.0.33-0+deb9u3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbs81664`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `scheduleLines`
--

CREATE TABLE `scheduleLines` (
  `id` int(11) NOT NULL,
  `idschedule` int(11) NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `scheduleLines`
--

INSERT INTO `scheduleLines` (`id`, `idschedule`, `starttime`, `endtime`) VALUES
(1, 1, '2019-06-05 09:00:00', '2019-06-05 15:00:00'),
(2, 1, '2019-06-05 17:00:00', '2019-06-05 19:00:00'),
(3, 2, '2019-06-05 15:00:00', '2019-06-05 20:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `schedulesHeader`
--

CREATE TABLE `schedulesHeader` (
  `id` int(11) NOT NULL,
  `schedule` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `schedulesHeader`
--

INSERT INTO `schedulesHeader` (`id`, `schedule`) VALUES
(1, 'mañanas'),
(2, 'tardes'),
(3, 'standard');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `signRegister`
--

CREATE TABLE `signRegister` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL,
  `idschedule` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `movtype` varchar(50) NOT NULL DEFAULT ''''''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `signRegister`
--

INSERT INTO `signRegister` (`id`, `iduser`, `idGroup`, `idschedule`, `date`, `movtype`) VALUES
(4, 22, 0, 0, '2019-06-08 22:36:11', 'ENTRADA'),
(5, 22, 0, 0, '2019-06-08 22:36:26', 'ENTRADA'),
(6, 22, 0, 0, '2019-06-08 22:36:30', 'ENTRADA'),
(7, 22, 0, 0, '2019-06-08 22:40:44', 'SALIDA'),
(8, 22, 0, 0, '2019-06-08 22:40:55', 'ENTRADA'),
(9, 22, 0, 0, '2019-06-08 22:41:02', 'SALIDA'),
(10, 22, 0, 0, '2019-06-08 22:46:31', 'ENTRADA'),
(11, 22, 0, 0, '2019-06-08 22:46:37', 'SALIDA'),
(12, 22, 0, 0, '2019-06-08 22:48:09', 'ENTRADA'),
(13, 22, 0, 0, '2019-06-08 22:48:17', 'SALIDA'),
(14, 22, 0, 0, '2019-06-08 22:49:09', 'ENTRADA'),
(15, 22, 0, 0, '2019-06-08 22:49:13', 'SALIDA'),
(16, 22, 0, 0, '2019-06-08 22:49:21', 'ENTRADA'),
(17, 22, 0, 0, '2019-06-08 22:49:25', 'SALIDA'),
(18, 23, 0, 0, '2019-06-08 00:00:00', 'ENTRADA'),
(19, 23, 0, 0, '2019-06-08 00:00:00', 'SALIDA'),
(20, 23, 0, 0, '2019-06-08 00:00:00', 'SALIDA'),
(21, 2, 0, 0, '2019-06-08 00:00:00', 'ENTRADA'),
(22, 2, 0, 0, '2019-06-08 00:00:00', 'SALIDA'),
(23, 23, 0, 0, '2019-06-08 00:00:00', 'SALIDA'),
(24, 23, 0, 0, '2019-06-08 23:39:14', 'SALIDA'),
(25, 23, 0, 0, '2019-06-08 23:39:14', 'ENTRADA'),
(26, 23, 0, 0, '2019-06-08 23:39:54', 'SALIDA'),
(27, 2, 0, 0, '2019-06-08 23:47:51', 'SALIDA'),
(28, 2, 0, 0, '2019-06-08 23:47:54', 'ENTRADA'),
(29, 23, 0, 0, '2019-06-08 23:48:14', 'ENTRADA'),
(30, 23, 0, 0, '2019-06-08 23:48:16', 'SALIDA'),
(31, 23, 0, 0, '2019-06-08 23:48:20', 'ENTRADA'),
(32, 23, 0, 0, '2019-06-08 23:48:21', 'SALIDA'),
(33, 23, 0, 0, '2019-06-08 23:48:25', 'ENTRADA'),
(34, 23, 0, 0, '2019-06-08 23:48:27', 'SALIDA'),
(35, 23, 0, 0, '2019-06-08 23:48:28', 'ENTRADA'),
(36, 23, 0, 0, '2019-06-08 23:48:32', 'SALIDA'),
(37, 23, 0, 0, '2019-06-08 23:51:23', 'ENTRADA'),
(38, 23, 0, 0, '2019-06-08 23:52:19', 'SALIDA'),
(39, 23, 0, 0, '2019-06-08 23:52:21', 'ENTRADA'),
(40, 23, 0, 0, '2019-06-08 23:52:22', 'SALIDA'),
(41, 23, 0, 0, '2019-06-08 23:52:23', 'ENTRADA'),
(42, 23, 0, 0, '2019-06-08 23:52:24', 'SALIDA'),
(43, 23, 0, 0, '2019-06-08 23:52:26', 'ENTRADA'),
(44, 23, 0, 0, '2019-06-08 23:52:27', 'SALIDA'),
(45, 23, 0, 0, '2019-06-08 23:52:28', 'ENTRADA'),
(46, 23, 0, 0, '2019-06-08 23:53:18', 'SALIDA'),
(47, 23, 0, 0, '2019-06-08 23:56:14', 'ENTRADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usergroups`
--

CREATE TABLE `usergroups` (
  `id` int(11) NOT NULL,
  `usergroup` varchar(500) NOT NULL,
  `idSchedule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usergroups`
--

INSERT INTO `usergroups` (`id`, `usergroup`, `idSchedule`) VALUES
(1, 'Profesores', 1),
(2, 'standard', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `administrator` bit(1) NOT NULL DEFAULT b'0',
  `usergroup` int(11) NOT NULL,
  `idSchedule` int(11) NOT NULL DEFAULT '0',
  `nfcCode` varchar(50) NOT NULL DEFAULT ''''''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `user`, `password`, `active`, `administrator`, `usergroup`, `idSchedule`, `nfcCode`) VALUES
(2, 'admin', 'admin', b'1', b'1', 0, 0, ''),
(23, 'Manu', 'test', b'1', b'0', 0, 0, '65C540E2');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vScheduleLines`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vScheduleLines` (
`id` int(11)
,`idschedule` int(11)
,`starttime` datetime
,`endtime` datetime
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vSchedulesHeader`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vSchedulesHeader` (
`id` int(11)
,`schedule` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vSchedulesLines`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vSchedulesLines` (
`idSchedule` int(11)
,`id` int(11)
,`schedule` varchar(250)
,`StartTime` datetime
,`EndTime` datetime
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vsignRegister`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vsignRegister` (
`id` int(11)
,`iduser` int(11)
,`idGroup` int(11)
,`idschedule` int(11)
,`date` datetime
,`movtype` varchar(50)
,`user` varchar(250)
,`usergroup` varchar(500)
,`schedule` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vsingRegisterUsers`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vsingRegisterUsers` (
`id` int(11)
,`user` varchar(250)
,`password` varchar(250)
,`active` bit(1)
,`administrator` bit(1)
,`usergroup` int(11)
,`idSchedule` int(11)
,`nfcCode` varchar(50)
,`movtype` varchar(7)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vusergroups`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vusergroups` (
`id` int(11)
,`usergroup` varchar(500)
,`idSchedule` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vusers`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vusers` (
`id` int(11)
,`user` varchar(250)
,`password` varchar(250)
,`active` bit(1)
,`administrator` bit(1)
,`usergroup` int(11)
,`idSchedule` int(11)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vScheduleLines`
--
DROP TABLE IF EXISTS `vScheduleLines`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vScheduleLines`  AS  select `scheduleLines`.`id` AS `id`,`scheduleLines`.`idschedule` AS `idschedule`,`scheduleLines`.`starttime` AS `starttime`,`scheduleLines`.`endtime` AS `endtime` from `scheduleLines` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vSchedulesHeader`
--
DROP TABLE IF EXISTS `vSchedulesHeader`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vSchedulesHeader`  AS  select `schedulesHeader`.`id` AS `id`,`schedulesHeader`.`schedule` AS `schedule` from `schedulesHeader` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vSchedulesLines`
--
DROP TABLE IF EXISTS `vSchedulesLines`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vSchedulesLines`  AS  select `lin`.`idschedule` AS `idSchedule`,`lin`.`id` AS `id`,`head`.`schedule` AS `schedule`,`lin`.`starttime` AS `StartTime`,`lin`.`endtime` AS `EndTime` from (`scheduleLines` `lin` join `schedulesHeader` `head` on((`head`.`id` = `lin`.`idschedule`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vsignRegister`
--
DROP TABLE IF EXISTS `vsignRegister`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vsignRegister`  AS  select `signRegister`.`id` AS `id`,`signRegister`.`iduser` AS `iduser`,`signRegister`.`idGroup` AS `idGroup`,`signRegister`.`idschedule` AS `idschedule`,`signRegister`.`date` AS `date`,`signRegister`.`movtype` AS `movtype`,ifnull(`users`.`user`,'Usuario borrado') AS `user`,ifnull(`usergroups`.`usergroup`,0) AS `usergroup`,ifnull(`schedulesHeader`.`schedule`,0) AS `schedule` from (((`signRegister` left join `users` on((`users`.`id` = `signRegister`.`iduser`))) left join `usergroups` on((`usergroups`.`id` = `signRegister`.`idGroup`))) left join `schedulesHeader` on((`schedulesHeader`.`id` = `signRegister`.`idschedule`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vsingRegisterUsers`
--
DROP TABLE IF EXISTS `vsingRegisterUsers`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vsingRegisterUsers`  AS  select `users`.`id` AS `id`,`users`.`user` AS `user`,`users`.`password` AS `password`,`users`.`active` AS `active`,`users`.`administrator` AS `administrator`,`users`.`usergroup` AS `usergroup`,`users`.`idSchedule` AS `idSchedule`,`users`.`nfcCode` AS `nfcCode`,if(((select ifnull(`signRegister`.`movtype`,'ENTRADA') from `signRegister` where (`signRegister`.`iduser` = `users`.`id`) order by `signRegister`.`date` desc limit 1) = 'ENTRADA'),'SALIDA','ENTRADA') AS `movtype` from `users` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vusergroups`
--
DROP TABLE IF EXISTS `vusergroups`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vusergroups`  AS  select `usergroups`.`id` AS `id`,`usergroups`.`usergroup` AS `usergroup`,`usergroups`.`idSchedule` AS `idSchedule` from `usergroups` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vusers`
--
DROP TABLE IF EXISTS `vusers`;

CREATE ALGORITHM=UNDEFINED DEFINER=`o81664`@`%` SQL SECURITY DEFINER VIEW `vusers`  AS  select `users`.`id` AS `id`,`users`.`user` AS `user`,`users`.`password` AS `password`,`users`.`active` AS `active`,`users`.`administrator` AS `administrator`,`users`.`usergroup` AS `usergroup`,`users`.`idSchedule` AS `idSchedule` from `users` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `scheduleLines`
--
ALTER TABLE `scheduleLines`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `schedulesHeader`
--
ALTER TABLE `schedulesHeader`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `signRegister`
--
ALTER TABLE `signRegister`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usergroups`
--
ALTER TABLE `usergroups`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `scheduleLines`
--
ALTER TABLE `scheduleLines`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `schedulesHeader`
--
ALTER TABLE `schedulesHeader`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `signRegister`
--
ALTER TABLE `signRegister`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT de la tabla `usergroups`
--
ALTER TABLE `usergroups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
