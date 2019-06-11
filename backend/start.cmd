@echo off
RD /S /Q target\docker
call mvnw.cmd package docker:build
REM echo %cd%
call docker-compose up