-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 24, 2024 lúc 06:31 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlbn`
--

CREATE DATABASE `qlbn`;

USE `qlbn`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--



CREATE TABLE `account` (
  `user` int(11) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phanquyen` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`user`, `password`, `phanquyen`) VALUES
(1, '1', 'AD');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tt_ba`
--

CREATE TABLE `tt_ba` (
  `Maba` int(11) NOT NULL,
  `Mabn` int(11) DEFAULT NULL,
  `Mabs` int(11) DEFAULT NULL,
  `Ngaynv` date DEFAULT NULL,
  `Ngayxv` date DEFAULT NULL,
  `Chandoan` varchar(500) DEFAULT NULL,
  `Tongtien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tt_bn`
--

CREATE TABLE `tt_bn` (
  `Mabn` int(11) NOT NULL,
  `HoTen` varchar(200) DEFAULT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `Cccd` varchar(50) DEFAULT NULL,
  `Bhyt` varchar(50) DEFAULT NULL,
  `Sdt` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `DiaChi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tt_bs`
--

CREATE TABLE `tt_bs` (
  `Mabs` int(11) NOT NULL,
  `Hoten` varchar(200) DEFAULT NULL,
  `Gioitinh` varchar(10) NOT NULL,
  `Ngaysinh` date DEFAULT NULL,
  `Cccd` varchar(50) DEFAULT NULL,
  `Sdt` varchar(50) DEFAULT NULL,
  `Email` varchar(200) NOT NULL,
  `Diachi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tt_dt`
--

CREATE TABLE `tt_dt` (
  `Madt` int(11) NOT NULL,
  `Maba` int(11) NOT NULL,
  `Thanhtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tt_thuoc`
--

CREATE TABLE `tt_thuoc` (
  `Madt` int(11) NOT NULL,
  `Tenthuoc` varchar(100) NOT NULL,
  `Soluong` int(11) DEFAULT NULL,
  `Dongia` int(11) DEFAULT NULL,
  `Thanhtien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`user`);

--
-- Chỉ mục cho bảng `tt_ba`
--
ALTER TABLE `tt_ba`
  ADD PRIMARY KEY (`Maba`),
  ADD KEY `Mabn` (`Mabn`),
  ADD KEY `Mabs` (`Mabs`);

--
-- Chỉ mục cho bảng `tt_bn`
--
ALTER TABLE `tt_bn`
  ADD PRIMARY KEY (`Mabn`);

--
-- Chỉ mục cho bảng `tt_bs`
--
ALTER TABLE `tt_bs`
  ADD PRIMARY KEY (`Mabs`);

--
-- Chỉ mục cho bảng `tt_dt`
--
ALTER TABLE `tt_dt`
  ADD PRIMARY KEY (`Madt`),
  ADD KEY `Maba` (`Maba`);

--
-- Chỉ mục cho bảng `tt_thuoc`
--
ALTER TABLE `tt_thuoc`
  ADD PRIMARY KEY (`Madt`,`Tenthuoc`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tt_ba`
--
ALTER TABLE `tt_ba`
  MODIFY `Maba` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `tt_bn`
--
ALTER TABLE `tt_bn`
  MODIFY `Mabn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT cho bảng `tt_bs`
--
ALTER TABLE `tt_bs`
  MODIFY `Mabs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10002;

--
-- AUTO_INCREMENT cho bảng `tt_dt`
--
ALTER TABLE `tt_dt`
  MODIFY `Madt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tt_ba`
--
ALTER TABLE `tt_ba`
  ADD CONSTRAINT `tt_ba_ibfk_1` FOREIGN KEY (`Mabn`) REFERENCES `tt_bn` (`Mabn`),
  ADD CONSTRAINT `tt_ba_ibfk_2` FOREIGN KEY (`Mabs`) REFERENCES `tt_bs` (`Mabs`);

--
-- Các ràng buộc cho bảng `tt_dt`
--
ALTER TABLE `tt_dt`
  ADD CONSTRAINT `tt_dt_ibfk_1` FOREIGN KEY (`Maba`) REFERENCES `tt_ba` (`Maba`);

--
-- Các ràng buộc cho bảng `tt_thuoc`
--
ALTER TABLE `tt_thuoc`
  ADD CONSTRAINT `tt_thuoc_ibfk_1` FOREIGN KEY (`Madt`) REFERENCES `tt_dt` (`Madt`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
