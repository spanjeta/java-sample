-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2018 at 01:17 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `droncall`
--

-- --------------------------------------------------------


--
-- Table structure for table `User-Registration`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `userImage` varchar(255) DEFAULT NULL,  
  `first_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,  
  `contactNumber` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `address varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)


)

 ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- -------------------------------------------------------- 


--
-- Table structure for table `Doctor`
--


CREATE TABLE `doctor` (
   `id` int(11) NOT NULL AUTO_INCREMENT,   
   `Experiance` varchar(11) DEFAULT NULL,
   `spicialization` varchar(255) DEFAULT NULL,
   `description` text
   `state_id` int(11) NOT NULL,
   `user_id` int(11) NOT NULL,
   `performance` int(11) NOT NULL DEFAULT '0',
   `activity_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_doctor_user_id` (`user_id`),
    CONSTRAINT `fk_doctor_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)         
  )

ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



-- -------------------------------------------------------- 

--
-- Table structure for table `Moderator`
--

CREATE TABLE `moderator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `idProof_type` int(11) NOT NULL,
  `idProof_number` varchar(255) NOT NULL,
  `id_proof_file` varchar(255) COLLATE utf8_unicode_ci NOT NULL,  
  `state_id` int(11) NOT NULL DEFAULT '0',
  `performance` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,    
          
  )

ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



-- -------------------------------------------------------- 

--
-- Table structure for table `Patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `idProof_type` int(11) NOT NULL,
  `idProof_number` varchar(255) NOT NULL,
  `id_proof_file` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nameOfAttendant` varchar(255) DEFAULT NULL,
  `referringDoctor` varchar(255) DEFAULT NULL,
  `state_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,   
  `doctor_id` int(11) NOT NULL,  
  `activity_id` int(11) NOT NULL,
   PRIMARY KEY (`id`),
    KEY `fk_patient_user_id` (`user_id`),
    CONSTRAINT `fk_patient_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)         
  )

ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -------------------------------------------------------- 

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text, 
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL
  `state_id` int(11) NOT NULL DEFAULT '0'

 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--------------------------------------------------------

--
-- Table structure for table `call_Log`
--

CREATE TABLE `callLog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `ended_on` datetime NOT NULL,  
  `call_duration` time DEFAULT NULL,
  `state_id int(11) NOT NULL DEFAULT '0',
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `session` varchar(1000) NOT NULL,
  `token` varchar(1000) NOT NULL  
 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (

    `id` int(11) NOT NULL AUTO_INCREMENT,
    `created_on` datetime NOT NULL,
    `updated_on` datetime NOT NULL DEFAULT '0',
    
    `patient_id` int(11) NOT NULL,
    `doctor_id` int(11) NOT NULL,

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--------------------------------------------------------

--
-- Table structure for table `activity_question`
--

CREATE TABLE `activity_question` (

    `id` int(11) NOT NULL AUTO_INCREMENT,
    `created_on` datetime NOT NULL,
    `updated_on` datetime NOT NULL DEFAULT '0',
    `activity_id` int(11) NOT NULL,
    `question` text,
    `answer_id` int(11) NOT NULL



) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (

    `id` int(11) NOT NULL AUTO_INCREMENT,
    `created_on` datetime NOT NULL,
    `updated_on` datetime NOT NULL DEFAULT '0',
    `question_id`  int(11) NOT NULL
    



) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




--------------------------------------------------------

--
-- Table structure for table `city_fee`
--

CREATE TABLE `city_fee` (

    `id` int(11) NOT NULL AUTO_INCREMENT,
    `created_on` datetime NOT NULL,
    `updated_on` datetime NOT NULL,
    `price` int(11) DEFAULT '0',
    `city` varchar(255) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (

    `id` int(11) NOT NULL AUTO_INCREMENT,
    `created_on` datetime NOT NULL,
    `state_id` int(11) NOT NULL DEFAULT '0', 
    `cost` int(11) DEFAULT '0'

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



--------------------------------------------------------


--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `appointment_time_on` time NOT NULL,
  `appointment_date_on` datetime NOT NULL,
  `state_id int(11) NOT NULL DEFAULT '0', 
  `is_paid` int(11) NOT NULL DEFAULT '0',
  `is_emergency` int(11) NOT NULL DEFAULT '0',
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL
   
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--------------------------------------------------------


--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `comment` text COLLATE utf8_unicode_ci,
  `doctor_id` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,  
  `state_id` int(11) NOT NULL DEFAULT '0',
  `patient_id` int(11) DEFAULT NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--------------------------------------------------------


--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
 
  `id` int(11) NOT NULL,
  `reason` varchar(1000) NOT NULL,
  `state_id` int(11) NOT NULL,  
  `created_on` datetime DEFAULT NULL,  
  `patient_id` int(11) DEFAULT NULL,
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;







