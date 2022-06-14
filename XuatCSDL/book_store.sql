-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 14, 2022 lúc 10:09 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `book_store`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `gprice` double NOT NULL,
  `id_tl` int(11) NOT NULL,
  `author` varchar(100) NOT NULL,
  `img_url` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `name`, `price`, `gprice`, `id_tl`, `author`, `img_url`) VALUES
(1, 'TỪ ĐIỂN TIẾNG “EM”', 58000, 69000, 3, 'Khotudien', 'https://newshop.vn/public/uploads/products/33677/tu-dien-tieng-em.jpg'),
(2, 'TỪ MAI HÃY LÀM NGƯỜI HẠNH PHÚC', 98000, 120000, 3, 'Toái Toái', 'https://newshop.vn/public/uploads/products/47684/sach-tu-ngay-mai-hay-lam-nguoi-hanh-phuc.jpg'),
(3, 'HẠNH PHÚC PHẢI GIẤU KÍN', 112000, 131000, 3, 'Vipvandan', 'https://newshop.vn/public/uploads/products/38780/hanh-phuc-phai-giau-kin-1.jpg'),
(4, '50 Thực Đơn Nấu Ăn Gia Đình', 43000, 74000, 6, 'VoDanh', 'https://newshop.vn/public/uploads/products/42306/sach-50-thuc-don-nau-an-gia-dinh.jpg'),
(5, '100 MÓN ĂN NGON RẺ', 44000, 70000, 6, 'Khong biet', 'https://newshop.vn/public/uploads/products/2123/100monanngonre_2012.jpg'),
(6, 'HIỂU VỀ TRÁI TIM', 124444, 138888, 1, '', 'https://newshop.vn/public/uploads/products/4232/sach-hieu-ve-trai-tim.jpg'),
(7, 'ĐƯỜNG XƯA MÂY TRẮNG', 153000, 190000, 1, '', 'https://newshop.vn/public/uploads/products/1009/sach-duong-xua-may-trang-bia-mem.jpg'),
(8, '99 THỬ THÁCH TỪ VỰNG', 320000, 347000, 1, '', 'https://newshop.vn/public/uploads/products/26444/99-thu-thach-tu-vung-1.jpg'),
(9, 'Trí Tuệ Kinh Doanh Châu Á', 79000, 99000, 4, '', 'https://newshop.vn/public/uploads/products/1377/tri-tue-kinh-doanh-chau-a-bia_L.jpg'),
(10, 'Những Bài Học Kinh Doanh Từ Loài Khỉ', 49000, 58000, 4, '', 'https://newshop.vn/public/uploads/products/1107/2d2db1aa9bd6a0614a114ec9669532cf_L.jpg'),
(11, 'KAMA SUTRA Trong Kinh Doanh', 44000, 55000, 4, '', 'https://newshop.vn/public/uploads/products/1103/kamasutratrongkinhdoanh_L.jpg'),
(12, 'Lập Kế Hoạch Kinh Doanh Thắng Lợi', 128000, 153000, 4, '', 'https://newshop.vn/public/uploads/products/1096/7c07a10bed1e541fd75b9b123e4f8156_L.jpg'),
(13, '80 Chiêu Thức Kinh Doanh Thành Công', 170000, 210000, 4, '', 'https://newshop.vn/public/uploads/products/1067/80chieuthuckinhdoanthanhcong_L.jpg'),
(14, 'Sam Watson-Cuộc Đời Kinh Doanh Tại Mỹ', 73000, 110000, 4, '', 'https://newshop.vn/public/uploads/products/1361/samwatsoncuocdoikinhdoanhtaimy0_L.jpg'),
(15, 'Ngữ Pháp Tiếng Anh Diễn Giải', 47000, 66000, 2, '', 'https://newshop.vn/public/uploads/products/613/nguphaptienganh0_L.jpg'),
(16, 'Ngữ Pháp Tiếng Anh Căn Bản', 40000, 59000, 2, '', 'https://newshop.vn/public/uploads/products/614/ngu-phap-tieng-anh-can-ban_L.jpg'),
(17, 'Tuyển Tập Đề Thi Môn Tiếng Anh', 102000, 122000, 2, '', 'https://newshop.vn/public/uploads/products/401/tuyentapdethimontienganh1_L.jpg'),
(18, '130 Bài Ngữ Pháp Tiếng Anh', 122000, 154000, 2, '', 'https://newshop.vn/public/uploads/products/309/English-Grammar-in-use_L.jpg'),
(19, 'Bảo Bình Khó Hiểu', 98000, 133000, 5, '', 'https://newshop.vn/public/uploads/products/48311/sach-12-manh-ghep-vu-tru-bao-binh-kho-hieu_L.jpg'),
(20, 'Bản Lĩnh Kim Ngưu', 134000, 155000, 5, '', 'https://newshop.vn/public/uploads/products/48302/sach-12-manh-ghep-vu-tru-ban-linh-kim-nguu_L.jpg'),
(21, '10 Quy Luật Cuộc Sống', 79000, 98000, 5, '', 'https://newshop.vn/public/uploads/products/6694/sach-10-quy-luat-cuoc-song_L.jpg'),
(22, 'Truyền Đạt Ngắn Gọn Bắt Trọn Người Nghe', 114000, 133000, 5, '', 'https://newshop.vn/public/uploads/products/48357/sach-truyen-dat-ngan-gon-bat-tron-nguoi-nghe_L.jpg'),
(23, 'Ăn Chay Tốt Cho Sức Khỏe', 78000, 93000, 6, '', 'https://newshop.vn/public/uploads/products/42217/sach-an-chay-tot-cho-suc-khoe_L.jpg'),
(24, '72 Món Gỏi', 99000, 105000, 6, '', 'https://newshop.vn/public/uploads/products/42310/sach-72-mon-goi_L.jpg'),
(25, 'Trí Tuệ Nội Tâm', 81000, 96000, 1, '', 'https://newshop.vn/public/uploads/products/48353/sach-tri-tue-noi-tam_L.jpg'),
(26, 'Mật Ngữ 12 Cung Hoàng Đạo', 220000, 245000, 3, '', 'https://newshop.vn/public/uploads/products/43853/sach-mat-ngu-12-cung-hoang-dao-bi-mat-cung-kim-nguu-ty-phu-bam-sinh_L.jpg'),
(27, 'Bên Kia Đường Có Đứa Dở Hơi', 61000, 75000, 3, '', 'https://newshop.vn/public/uploads/products/28304/sach-ben-kia-duong-co-dua-do-hoi_L.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `card`
--

CREATE TABLE `card` (
  `id` int(11) NOT NULL,
  `total_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `card`
--

INSERT INTO `card` (`id`, `total_price`) VALUES
(1, 0),
(2, 0),
(8, NULL),
(9, NULL),
(13, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietcard`
--

CREATE TABLE `chitietcard` (
  `id` int(11) NOT NULL,
  `id_card` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `image_url` varchar(300) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `sl` int(11) NOT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietcard`
--

INSERT INTO `chitietcard` (`id`, `id_card`, `id_book`, `image_url`, `name`, `price`, `sl`, `total_price`) VALUES
(87, 9, 23, 'https://newshop.vn/public/uploads/products/42217/sach-an-chay-tot-cho-suc-khoe_L.jpg', 'Ăn Chay Tốt Cho Sức Khỏe', 78000, 3, 234000),
(105, 2, 2, 'https://newshop.vn/public/uploads/products/47684/sach-tu-ngay-mai-hay-lam-nguoi-hanh-phuc.jpg', 'TỪ MAI HÃY LÀM NGƯỜI HẠNH PHÚC', 98000, 5, 490000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `id_donhang` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `image_url` varchar(300) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `sl` int(11) NOT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `id_donhang`, `id_book`, `image_url`, `name`, `price`, `sl`, `total_price`) VALUES
(61, 55, 25, 'https://newshop.vn/public/uploads/products/48353/sach-tri-tue-noi-tam_L.jpg', 'Trí Tuệ Nội Tâm', 81000, 5, 405000),
(68, 62, 24, 'https://newshop.vn/public/uploads/products/42310/sach-72-mon-goi_L.jpg', '72 Món Gỏi', 99000, 3, 297000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctcard`
--

CREATE TABLE `ctcard` (
  `id` int(11) NOT NULL,
  `id_card` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_time` text NOT NULL,
  `price` double NOT NULL,
  `trangthai` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `id_user`, `date_time`, `price`, `trangthai`) VALUES
(55, 1, 'Th 2, 13 thg 6 2022, 16:08', 405000, 'Chờ xác nhận'),
(62, 8, 'Th 2, 13 thg 6 2022, 22:36', 297000, 'Chờ xác nhận');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`id`, `name`) VALUES
(1, 'Mới phát hành'),
(2, 'Tiếng anh'),
(3, 'Tình yêu'),
(4, 'Kinh doanh'),
(5, 'Xã hội'),
(6, 'Nấu ăn'),
(7, 'Mẹo vặt'),
(8, 'Đời sống'),
(9, 'cổ tích'),
(10, 'Truyện tranh'),
(11, 'Tiểu thuyết'),
(12, 'Ngoại ngữ'),
(13, 'Gia đình'),
(14, 'Thế giới'),
(15, 'Văn học'),
(16, 'Toán học'),
(17, 'Sử học'),
(18, 'Thiếu nhi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `sdt` text NOT NULL,
  `mail` text NOT NULL,
  `address` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `uname`, `name`, `pass`, `sdt`, `mail`, `address`) VALUES
(1, 'tiepdvbk', 'Nguyễn Phúc Tiệpp', '123', '84389529333', 'npt260400@gmail.com', 'Trường đại học CNTT và TT Thái nguyên, Xã Quyết Thắng, TP Thái Nguyên, Thái Nguyên'),
(2, '2', 'Vô danh', '2', '84332390345', 'tiepdvbk1@gmail.com', NULL),
(8, 'test', 'Vip Test', '123', '0389529333', 'npt2604@gmail.com', NULL),
(9, 'test2', 'test2', 'test2', '04343333', 'nsuaa@gmail.com', NULL),
(13, 'ictu', 'Cntt và TT', '123', '03546184949', 'ictu20223@gmail.com', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tl` (`id_tl`);

--
-- Chỉ mục cho bảng `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `chitietcard`
--
ALTER TABLE `chitietcard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_card` (`id_card`),
  ADD KEY `id_book` (`id_book`);

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_book` (`id_book`),
  ADD KEY `id_donhang` (`id_donhang`);

--
-- Chỉ mục cho bảng `ctcard`
--
ALTER TABLE `ctcard`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `chitietcard`
--
ALTER TABLE `chitietcard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT cho bảng `ctcard`
--
ALTER TABLE `ctcard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT cho bảng `theloai`
--
ALTER TABLE `theloai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`id_tl`) REFERENCES `theloai` (`id`);

--
-- Các ràng buộc cho bảng `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `chitietcard`
--
ALTER TABLE `chitietcard`
  ADD CONSTRAINT `chitietcard_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `card` (`id`),
  ADD CONSTRAINT `chitietcard_ibfk_2` FOREIGN KEY (`id_book`) REFERENCES `book` (`id`);

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_2` FOREIGN KEY (`id_book`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `chitietdonhang_ibfk_3` FOREIGN KEY (`id_donhang`) REFERENCES `donhang` (`id`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
