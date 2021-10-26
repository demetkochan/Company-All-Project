-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 26 Eki 2021, 16:46:47
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `company`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `advertising`
--

CREATE TABLE `advertising` (
  `id` int(11) NOT NULL,
  `advtitle` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `screentime` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `advertising`
--

INSERT INTO `advertising` (`id`, `advtitle`, `endtime`, `height`, `image_name`, `link`, `screentime`, `starttime`, `width`) VALUES
(1, 'Araba Reklamı', '2021-10-14', '360', '2e2406a9-2857-4cf0-9837-b5e92d17997ao.jpg', 'https://honda.com.tr/', '2', '2021-10-13', '400'),
(3, 'Telefon Reklamı', '2021-10-16', '360', '2dcb3e03-4f94-4d26-83d1-f16d4640c0f4o.jpg', 'https://honda.com.tr/', '2', '2021-10-15', '400'),
(5, 'Honda Reklamı', '2021-10-26', '360', '6f0e65cc-2483-4506-83f7-c3a871bd78b9a.jpg', 'https://honda.com.tr/', '2', '2021-10-26', '400');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `announcement`
--

CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `announcement_date` datetime DEFAULT NULL,
  `announcement_detail_desc` varchar(255) DEFAULT NULL,
  `announcement_status` int(11) NOT NULL,
  `announcementtitle` varchar(255) DEFAULT NULL,
  `category_product_id` int(11) DEFAULT NULL,
  `category_announcement_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `announcement`
--

INSERT INTO `announcement` (`id`, `announcement_date`, `announcement_detail_desc`, `announcement_status`, `announcementtitle`, `category_product_id`, `category_announcement_id`) VALUES
(1, '2021-10-11 03:00:00', 'Bu bir duyurudur', 1, 'Duyuru', NULL, NULL),
(3, '2021-10-12 03:00:00', 'Bu Duyuru Mesajıdır', 2, 'Duyuru3', NULL, NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `category_announcement`
--

CREATE TABLE `category_announcement` (
  `id` int(11) NOT NULL,
  `newscategoryname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `category_announcement`
--

INSERT INTO `category_announcement` (`id`, `newscategoryname`) VALUES
(4, 'Spor'),
(5, ' Teknoloji'),
(6, ' Bilim'),
(7, 'Ekonomi'),
(8, ' Siyaset'),
(9, ' Hukuk'),
(10, 'Denemee'),
(11, 'Tarım');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `category_gallery`
--

CREATE TABLE `category_gallery` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `gallerycategoryname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `category_gallery`
--

INSERT INTO `category_gallery` (`id`, `category_name`, `gallerycategoryname`) VALUES
(1, NULL, 'Araba'),
(2, NULL, ' Müşteri'),
(3, NULL, 'Telefon');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `category_product`
--

CREATE TABLE `category_product` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `productcategoryname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `category_product`
--

INSERT INTO `category_product` (`id`, `category_name`, `productcategoryname`) VALUES
(1, NULL, 'Teknoloji'),
(2, NULL, ' Tekstil'),
(3, NULL, 'Spor');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `contact`
--

CREATE TABLE `contact` (
  `cid` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `contact`
--

INSERT INTO `contact` (`cid`, `created_date`, `email`, `message`, `name`) VALUES
(1, '2021-10-20 19:14:09', 'demet@mail.com', 'Uygulama çok güzel bir şekilde yapılmış', 'Demet Koçhan'),
(2, '2021-10-26 13:17:33', 'erkan@mail.com', 'yyyyy', 'Erkan');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `content`
--

CREATE TABLE `content` (
  `id` int(11) NOT NULL,
  `content_date` datetime DEFAULT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  `content_detail_desc` varchar(255) DEFAULT NULL,
  `content_status` int(11) NOT NULL,
  `contenttitle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `content`
--

INSERT INTO `content` (`id`, `content_date`, `content_desc`, `content_detail_desc`, `content_status`, `contenttitle`) VALUES
(1, '2021-10-10 03:00:00', 'Bu bir', 'Ama ben bir reklamcı değilim ki diye bir düşünceye kapılmadan önce bir kendinizi dinleyin. Girişiminiz ya da işletmeniz için kaliteli içerik üretmek ve potansiyel müşteri kitlenizle doğru iletişimi kurmak istiyorsunuz, değil mi? ', 1, 'Deneme'),
(2, '2021-10-11 03:00:00', ' Denemeee', 'yyyyyy', 1, ' Deneme3'),
(5, '2021-10-10 03:00:00', 'Bu bir denemee', 'hhhhhhhhh', 2, 'Deneme5'),
(6, '2021-10-10 03:00:00', 'deneme 6', 'şişişişişişi', 2, 'Deneme6'),
(8, '2021-10-26 03:00:00', 'Denemeeee', 'Denemeeee', 1, 'Denemeee'),
(9, '2021-10-26 03:00:00', 'İçerik', 'Denemee', 1, 'içerik3');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `cemail` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `cphone` varchar(255) DEFAULT NULL,
  `csurname` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `token_expired` bit(1) NOT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`id`, `cemail`, `cname`, `cphone`, `csurname`, `enabled`, `token_expired`, `status`) VALUES
(1, 'veli@mail.com', 'Veli', '98654', 'Bilmem', b'1', b'1', b'1'),
(2, 'ali@mailcom', 'Ali', '767454', 'Bilir', b'1', b'1', b'1'),
(3, 'zehra@mail.com', 'Zehra', '677456', 'Yılmaz', b'1', b'1', b'1'),
(4, 'hasan@mail.com', 'Hasan', '564564', 'Bilirki', b'1', b'1', b'1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customers_roles`
--

CREATE TABLE `customers_roles` (
  `customer_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `customers_roles`
--

INSERT INTO `customers_roles` (`customer_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `gallery`
--

CREATE TABLE `gallery` (
  `id` int(11) NOT NULL,
  `gallery_detail` varchar(255) DEFAULT NULL,
  `gallery_status` int(11) NOT NULL,
  `galleryname` varchar(255) DEFAULT NULL,
  `category_gallery_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `gallery`
--

INSERT INTO `gallery` (`id`, `gallery_detail`, `gallery_status`, `galleryname`, `category_gallery_id`) VALUES
(1, 'Müşterilerimizin ürünleri', 1, 'Müşterilerimiz', 2),
(3, ' Ferrarinin arabaları', 1, ' Ferrariler', 1),
(5, 'Iphone Telefonları', 1, 'Iphone X', 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `gallery_image`
--

CREATE TABLE `gallery_image` (
  `gid` int(11) NOT NULL,
  `g_id` int(11) NOT NULL,
  `gallery_image` varchar(255) DEFAULT NULL,
  `image_desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `gallery_image`
--

INSERT INTO `gallery_image` (`gid`, `g_id`, `gallery_image`, `image_desc`) VALUES
(2, 2, '8c423a2f-cb9a-4a1b-8701-86c105f051c8a.jpg', 'Dünya resmi'),
(3, 1, '7851e6fc-8a36-47b5-82b6-1b8744385e4ba.jpg', 'Dünya resmi'),
(4, 4, '61485409-c692-45f7-bf9c-a50ec202f059o.jpg', 'yyyy'),
(5, 5, 'b991057c-5795-4cf8-9598-8fccfed4dfa4e.jpg', 'İphone Telefonu');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `l_date` datetime DEFAULT NULL,
  `l_ip` varchar(255) DEFAULT NULL,
  `l_url` varchar(255) DEFAULT NULL,
  `lemail` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `lroles` varchar(255) DEFAULT NULL,
  `lsession_id` varchar(255) DEFAULT NULL,
  `lsurname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `news`
--

CREATE TABLE `news` (
  `n_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `news_category` int(11) NOT NULL,
  `news_desc` varchar(255) DEFAULT NULL,
  `news_detail_desc` varchar(255) DEFAULT NULL,
  `news_image` varchar(255) DEFAULT NULL,
  `news_status` int(11) NOT NULL,
  `newstitle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `news`
--

INSERT INTO `news` (`n_id`, `created_date`, `news_category`, `news_desc`, `news_detail_desc`, `news_image`, `news_status`, `newstitle`) VALUES
(1, '2021-10-12 23:04:57', 4, 'Fenerbahçe Maçı', '<p>Fenerbah&ccedil;ee Şampiyonluk ma&ccedil;ını kazandı</p>\r\n', 'b9c97e1d-f7ec-4269-8eae-2fa9efac274co.jpg', 1, 'Fenerbahçe'),
(2, '2021-10-13 15:21:28', 6, 'Jeff Bezos Uzaya çıktı', '<p><strong>D&uuml;nya y&ouml;r&uuml;ngesinde 10 g&uuml;n</strong></p>\r\n', '43968ab6-07a2-4b02-bb71-2305c1613234a.jpg', 2, 'Uzay Yarışları'),
(4, '2021-10-15 20:34:59', 5, 'DOLAR UÇTU', '<p><s>Dolar 9.20</s></p>\r\n', '2b74cce3-6b3e-4f05-a03d-43c225cd7874o.jpg', 1, 'DOLARR'),
(6, '2021-10-25 20:02:19', 5, 'DOLAR UÇTU', '<p>Dolar u&ccedil;tu</p>\r\n', 'cf33a4a4-7a5c-4bf8-a22f-5638d08c9a02a.jpg', 1, 'Ekonomi'),
(7, '2021-10-26 13:33:48', 7, 'Haberdirr', '<p><s>Haberdirr</s></p>\r\n', '030faa4d-15af-4333-b94d-6031bf095f5fa.jpg', 1, 'Ekonomii'),
(8, '2021-10-26 16:27:19', 8, 'Teknoloji ürünlerine zam geldi', '<p>Telefon, tablet gibi teknolojik cihazlara %5 oranında zam uygulanacak</p>\r\n', 'af9987c2-f17c-4f13-8c39-a824a4f29258e.jpg', 1, 'Zamlar');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `order_box`
--

CREATE TABLE `order_box` (
  `oid` int(11) NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `order_count` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_product` int(11) NOT NULL,
  `order_status` int(11) NOT NULL,
  `order_customer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `order_box`
--

INSERT INTO `order_box` (`oid`, `customer_address`, `order_count`, `order_date`, `order_product`, `order_status`, `order_customer`) VALUES
(4, 'İstanbul/Türkiye', 2, '2021-10-22 03:00:00', 1, 1, 2),
(5, ' Trabzon/Türkiye', 1, '2021-10-23 03:00:00', 2, 2, 1),
(7, 'İzmir', 2, '2021-10-26 03:00:00', 5, 1, 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `campaign` int(11) NOT NULL,
  `campaign_desc` varchar(255) NOT NULL,
  `campaign_name` varchar(255) NOT NULL,
  `latitude` bigint(20) NOT NULL,
  `longitude` bigint(20) NOT NULL,
  `productli̇ke` int(11) NOT NULL,
  `product_desc` varchar(255) NOT NULL,
  `product_detail` varchar(255) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_type` int(11) NOT NULL,
  `productname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `product`
--

INSERT INTO `product` (`id`, `address`, `campaign`, `campaign_desc`, `campaign_name`, `latitude`, `longitude`, `productli̇ke`, `product_desc`, `product_detail`, `product_price`, `product_type`, `productname`) VALUES
(1, 'İstanbul/Türkiye', 2, 'Kampanya yok', 'Kampanya yok', 34, 42, 0, '64 GB', 'Samsung Galaxy s11', 6000, 1, 'Android Telefon'),
(2, 'Trabzon/Türkiye', 2, 'Kampanya yok', 'Kampanya Yok', 34, 35, 1, 'Apple Saat', 'Su geçirmez saat', 2000, 1, 'Akıllı Saatx'),
(5, 'İstanbul/Türkiye', 2, 'Kampanya Yok', 'Kampanya Yok', 23, 32, 1, 'Nike', 'Airmax', 800, 1, 'Spor Ayakkabı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `products_categories`
--

CREATE TABLE `products_categories` (
  `product_id` int(11) NOT NULL,
  `products_categories_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `products_categories`
--

INSERT INTO `products_categories` (`product_id`, `products_categories_id`) VALUES
(1, 1),
(2, 2),
(5, 2),
(5, 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `product_image`
--

CREATE TABLE `product_image` (
  `p_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `product_image`
--

INSERT INTO `product_image` (`p_id`, `product_id`, `product_image`) VALUES
(5, 1, 'a0ca6af9-d8ed-47dd-b571-274e93eed589o.jpg'),
(6, 1, '458e651f-01f8-452a-a2d7-1371ceb3994ao.jpg'),
(8, 2, 'c37a9cf3-f3e5-41fc-9302-8db8bfe5058ba.jpg'),
(10, 5, '4804b2b9-65fd-4f28-adf8-cc0480f75469e.jpg');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_CUSTOMER'),
(2, 'ROLE_MVC'),
(3, 'ROLE_REST'),
(4, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `survey`
--

CREATE TABLE `survey` (
  `id` int(11) NOT NULL,
  `surveytitle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `survey`
--

INSERT INTO `survey` (`id`, `surveytitle`) VALUES
(1, 'Macbook Pro'),
(2, 'Honda CRV'),
(4, 'Nike Ayakkabı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `survey_option`
--

CREATE TABLE `survey_option` (
  `id` int(11) NOT NULL,
  `optiontitle` varchar(255) DEFAULT NULL,
  `survey_id` int(11) NOT NULL,
  `optioncount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `survey_option`
--

INSERT INTO `survey_option` (`id`, `optiontitle`, `survey_id`, `optioncount`) VALUES
(1, 'İyi', 1, 2),
(3, 'Çok iyi', 2, 1),
(4, ' Orta', 2, 2),
(5, 'Kötü', 1, 1),
(6, 'Orta', 1, 1),
(7, 'Kötü', 2, 0),
(9, 'YYYY', 1, 0),
(10, 'Çok İyi', 4, 0),
(11, ' Ortalama', 4, 0),
(12, 'Kötü', 4, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `token_expired` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `company_address`, `company_name`, `email`, `enabled`, `first_name`, `last_name`, `password`, `phone`, `sector`, `token_expired`) VALUES
(1, 'Kadıköy/İstanbul', 'A', 'm@mail.com', b'1', 'Ali', 'Bilmem', '$2a$10$qSqOWmfhTVcLp6cFzEX4xum/zejJ5SRtuvgxD3nSRXvkqXIga5ixO', '8585858585', '3', b'1'),
(2, 'Kadıköy/İstanbul', 'B', 'v@mail.com', b'1', 'Veli', 'Kaz', '$2a$10$5YkdTWcjdTXH/RtTCgOaaOhYy//WzfM21nkw/8Uu1se2f3bcFOoM6', '999999999', '3', b'1'),
(3, 'Kadıköy/İstanbul', 'Bilgisayar A.Ş', 'serkan@mail.com', b'1', 'Serkanx', 'Kay', '$2a$10$ofhCjO1LAIwzu9UBRuGR0eRsakhAOGDWhUzVDGFNPPTeKqtkANhxe', '4864864865', '2', b'1'),
(13, 'Trabzon/Türkiye', 'F', 'hasan@mail.com', b'1', 'Hasan', 'Yılmaz', '$2a$10$8/3VUjbWFmgSB6rr1lUjm.DPlGGX8kOWllwwqX9h05r8NntSc2pw6', '7655389', '2', b'1');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 3),
(13, 2),
(3, 2);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `advertising`
--
ALTER TABLE `advertising`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `announcement`
--
ALTER TABLE `announcement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbjqfyyjs2tun6fa5p2m709pnq` (`category_product_id`),
  ADD KEY `FKpdklu3usr1rk8xmtpeq460cjm` (`category_announcement_id`);

--
-- Tablo için indeksler `category_announcement`
--
ALTER TABLE `category_announcement`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `category_gallery`
--
ALTER TABLE `category_gallery`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `category_product`
--
ALTER TABLE `category_product`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`cid`);

--
-- Tablo için indeksler `content`
--
ALTER TABLE `content`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `customers_roles`
--
ALTER TABLE `customers_roles`
  ADD KEY `FKmm7c7ctyfwsfot86evtmvsnp1` (`role_id`),
  ADD KEY `FKlgfi66wahqd5qlunf8dpknwg2` (`customer_id`);

--
-- Tablo için indeksler `gallery`
--
ALTER TABLE `gallery`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf8haondwy3hqgqgabgp2092ir` (`category_gallery_id`);

--
-- Tablo için indeksler `gallery_image`
--
ALTER TABLE `gallery_image`
  ADD PRIMARY KEY (`gid`);

--
-- Tablo için indeksler `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`n_id`);

--
-- Tablo için indeksler `order_box`
--
ALTER TABLE `order_box`
  ADD PRIMARY KEY (`oid`),
  ADD KEY `FK8owpybbd3n019pdxa2btjp54f` (`order_customer`);

--
-- Tablo için indeksler `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `products_categories`
--
ALTER TABLE `products_categories`
  ADD KEY `FKh9ovvddaghaq02xckx3mvno0i` (`products_categories_id`),
  ADD KEY `FKt1s2ikavb75cb1b60jvvmjqr5` (`product_id`);

--
-- Tablo için indeksler `product_image`
--
ALTER TABLE `product_image`
  ADD PRIMARY KEY (`p_id`);

--
-- Tablo için indeksler `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `survey`
--
ALTER TABLE `survey`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `survey_option`
--
ALTER TABLE `survey_option`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `advertising`
--
ALTER TABLE `advertising`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `announcement`
--
ALTER TABLE `announcement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `category_announcement`
--
ALTER TABLE `category_announcement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `category_gallery`
--
ALTER TABLE `category_gallery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `category_product`
--
ALTER TABLE `category_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `contact`
--
ALTER TABLE `contact`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `content`
--
ALTER TABLE `content`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `gallery`
--
ALTER TABLE `gallery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `gallery_image`
--
ALTER TABLE `gallery_image`
  MODIFY `gid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `news`
--
ALTER TABLE `news`
  MODIFY `n_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `order_box`
--
ALTER TABLE `order_box`
  MODIFY `oid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Tablo için AUTO_INCREMENT değeri `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `product_image`
--
ALTER TABLE `product_image`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `survey`
--
ALTER TABLE `survey`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `survey_option`
--
ALTER TABLE `survey_option`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `announcement`
--
ALTER TABLE `announcement`
  ADD CONSTRAINT `FKbjqfyyjs2tun6fa5p2m709pnq` FOREIGN KEY (`category_product_id`) REFERENCES `category_product` (`id`),
  ADD CONSTRAINT `FKpdklu3usr1rk8xmtpeq460cjm` FOREIGN KEY (`category_announcement_id`) REFERENCES `category_announcement` (`id`);

--
-- Tablo kısıtlamaları `customers_roles`
--
ALTER TABLE `customers_roles`
  ADD CONSTRAINT `FKlgfi66wahqd5qlunf8dpknwg2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKmm7c7ctyfwsfot86evtmvsnp1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Tablo kısıtlamaları `gallery`
--
ALTER TABLE `gallery`
  ADD CONSTRAINT `FKf8haondwy3hqgqgabgp2092ir` FOREIGN KEY (`category_gallery_id`) REFERENCES `category_gallery` (`id`);

--
-- Tablo kısıtlamaları `order_box`
--
ALTER TABLE `order_box`
  ADD CONSTRAINT `FK8owpybbd3n019pdxa2btjp54f` FOREIGN KEY (`order_customer`) REFERENCES `customer` (`id`);

--
-- Tablo kısıtlamaları `products_categories`
--
ALTER TABLE `products_categories`
  ADD CONSTRAINT `FKh9ovvddaghaq02xckx3mvno0i` FOREIGN KEY (`products_categories_id`) REFERENCES `category_product` (`id`),
  ADD CONSTRAINT `FKt1s2ikavb75cb1b60jvvmjqr5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Tablo kısıtlamaları `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
