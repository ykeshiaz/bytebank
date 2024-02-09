@echo off

title Create golf database

SET PGPASSWORD=postgres1

psql -U postgres -f dropdb.sql
createdb -U postgres capstone
psql -U postgres -d capstone -f schema.sql
psql -U postgres -d capstone -f user.sql
psql -U postgres -d capstone -f data.sql

pause
