-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Авг 10 2020 г., 14:52
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(50) NOT NULL,
  `comment` varchar(250) NOT NULL,
  `agree` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `name`, `gender`, `address`, `comment`, `agree`) VALUES
(1, 'login', 'password', 'Katya', '', '', '', 0),
(2, 'test', 'pass', 'somename', 'M', 'ashkbasdhj', 'kjasdnkjdsfbnjhdasbfhjabsfhjb', 1),
(3, 'user2', 'dsfdsf', 'sdfdsf', 'M', 'sdfdf', 'sdfdsf', 1),
(4, 'qwerty@bigmir.net', '123456789qQ', 'Alex', 'male', 'crimea', '88005553535 legche pozvonit chem u kogo-to zanimat\'!', 1),
(6, '123@dd.com', '-33e72edc99dc8a13f51d8a4af31433ca', 'Alex', 'female', 'dnr', 'asdfdas', 1),
(7, 'test@gmail.com', '123456789aA', 'Test', 'male', 'dnr', 'comment', 1),
(8, '15@fhfhf.org', '123456789aA', 'user144', 'male', 'crimea', 'comments', 1),
(9, '12@sdasd.uaa', '123456789aD', 'user44', 'female', 'dnr', 'i disagree!', 1),
(10, 'vanya@gmail.com', '-33e72edc99dc8a13f51d8a4af31433ca', 'Ivan', 'male', 'dnr', 'test coment', 1),
(11, 'vanya@gmail.com', '-29effb1350aa78eec77c138d4bed28e7', 'Ivan The Second', 'female', 'crimea', 'asdasd asdasd asd ', 1),
(12, 'sanya@gmail.com', '-29effb1350aa78eec77c138d4bed28e7', 'Alexa641', 'male', 'crimea', 'asdasddas', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
