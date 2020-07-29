-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июл 29 2020 г., 16:24
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
-- Структура таблицы `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `customers`
--

INSERT INTO `customers` (`id`, `name`, `age`) VALUES
(1, 'Katya', 20),
(2, 'Seryoja', 22);

-- --------------------------------------------------------

--
-- Структура таблицы `heroes`
--

CREATE TABLE `heroes` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `level` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `heroes`
--

INSERT INTO `heroes` (`id`, `name`, `level`, `item_id`) VALUES
(1, 'Pudge', 30, 0),
(2, 'Zeus', 5, 0),
(4, 'CrystalMaiden', 11, 0),
(5, 'Sniper', 7, 0),
(6, 'Sniper', 7, 0),
(7, 'Sniper', 7, 0),
(8, 'Pudge', 15, 3),
(9, 'Pudge', 1, 5);

-- --------------------------------------------------------

--
-- Структура таблицы `item`
--

CREATE TABLE `item` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `item`
--

INSERT INTO `item` (`ID`, `NAME`) VALUES
(1, 'Meat Hook'),
(2, 'Meat Hook'),
(3, 'Meat Hook'),
(4, 'Meat Hook'),
(5, 'Meat Hook');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `product_id`) VALUES
(1, 1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`id`, `name`, `price`) VALUES
(1, 'MacBook', 1),
(2, 'IPhone', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `t1`
--

CREATE TABLE `t1` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t1`
--

INSERT INTO `t1` (`id`, `name`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C');

-- --------------------------------------------------------

--
-- Структура таблицы `t2`
--

CREATE TABLE `t2` (
  `id` int(11) NOT NULL,
  `nick` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t2`
--

INSERT INTO `t2` (`id`, `nick`) VALUES
(2, 'D'),
(3, 'E'),
(4, 'F');

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
(4, 'qwerty@bigmir.net', '123456789qQ', 'Alex', 'male', 'crimea', '88005553535 legche pozvonit chem u kogo-to zanimat\'!', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `heroes`
--
ALTER TABLE `heroes`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `heroes`
--
ALTER TABLE `heroes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `item`
--
ALTER TABLE `item`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
