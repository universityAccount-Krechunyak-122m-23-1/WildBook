-- phpMyAdmin SQL Dump
-- version 5.1.4
-- https://www.phpmyadmin.net/
--
-- Хост: ieugene.mysql.ukraine.com.ua
-- Время создания: Май 16 2022 г., 10:34
-- Версия сервера: 5.7.33-36-log
-- Версия PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `ieugene_wildbook`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Clients`
--

CREATE TABLE `Clients` (
  `id` int(11) NOT NULL,
  `name` varchar(400) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(400) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Clients`
--

INSERT INTO `Clients` (`id`, `name`, `phone_number`, `email`) VALUES
(1, 'Альберт', '+380668761542', 'AlbertEmailExample@gmail.com'),
(2, 'Жанна', '+380988765744', 'JannaEmailExample@gmail.com'),
(3, 'Ентоні', '+380972366124', 'AntonyEmailExample@gmail.com');

-- --------------------------------------------------------

--
-- Структура таблицы `Operators`
--

CREATE TABLE `Operators` (
  `id` int(11) NOT NULL,
  `name` varchar(400) DEFAULT NULL,
  `is_Available` tinyint(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Operators`
--

INSERT INTO `Operators` (`id`, `name`, `is_Available`) VALUES
(1, 'Дмитро', 1),
(2, 'Ольга', 1),
(3, 'Антон', 0),
(4, 'Вікторія', 0),
(5, 'Тетяна', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Operators_Reports`
--

CREATE TABLE `Operators_Reports` (
  `ticket_id` int(11) NOT NULL,
  `operator_id` int(11) NOT NULL,
  `is_Active_Ticket` tinyint(1) NOT NULL DEFAULT '0',
  `comment` varchar(2000) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Operators_Reports`
--

INSERT INTO `Operators_Reports` (`ticket_id`, `operator_id`, `is_Active_Ticket`, `comment`) VALUES
(1, 1, 0, 'Книжка була успішно отримана клієнтом'),
(2, 4, 1, 'Очікуємо на клієнта аби він отримав свою книжку'),
(3, 3, 1, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `Tickets`
--

CREATE TABLE `Tickets` (
  `id` int(11) NOT NULL,
  `name` varchar(400) NOT NULL,
  `client_phone_number` varchar(400) DEFAULT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Tickets`
--

INSERT INTO `Tickets` (`id`, `name`, `client_phone_number`, `status`) VALUES
(1, 'Гормони щастя', '+380668761542', 'Вирішен'),
(2, 'Ловці неба', '+380988765744', 'В процесі'),
(3, 'Дім на краю ночі', '+380972366124', 'Очікування');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Clients`
--
ALTER TABLE `Clients`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Operators`
--
ALTER TABLE `Operators`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Operators_Reports`
--
ALTER TABLE `Operators_Reports`
  ADD PRIMARY KEY (`ticket_id`,`operator_id`),
  ADD KEY `fk_operator_id` (`operator_id`);

--
-- Индексы таблицы `Tickets`
--
ALTER TABLE `Tickets`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Clients`
--
ALTER TABLE `Clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `Operators`
--
ALTER TABLE `Operators`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `Tickets`
--
ALTER TABLE `Tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
