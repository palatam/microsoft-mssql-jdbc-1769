CREATE DATABASE [example];
CREATE LOGIN [example] WITH PASSWORD = 'AsdfQwer1234!#+-';
CREATE USER [example] FOR LOGIN [example];
USE [example];
ALTER AUTHORIZATION ON DATABASE::[example] TO [example];
