@echo off
REM === Configurations ===
set TOMCAT_PATH=D:\apache-tomcat-10.1.28
set WAR_NAME=ma-bibliotheque
set PROJECT_DIR=%~dp0
set TARGET_WAR=%PROJECT_DIR%target\%WAR_NAME%.war

echo ========================================
echo =   Déploiement de %WAR_NAME%.war dans Tomcat
echo ========================================

REM === Étape 1: Compiler le projet ===
echo Compilation Maven...
cd /d %PROJECT_DIR%
call mvn clean package

IF NOT EXIST "%TARGET_WAR%" (
    echo ERREUR : Le fichier %WAR_NAME%.war n'a pas été généré !
    pause
    exit /b
)

REM === Étape 2: Copier le .war dans Tomcat ===
echo Copie du fichier .war dans Tomcat...
copy /Y "%TARGET_WAR%" "%TOMCAT_PATH%\webapps\"

REM === Étape 3: Redémarrer Tomcat ===
echo Redémarrage de Tomcat...
cd "%TOMCAT_PATH%\bin\"
@REM shutdown.bat
timeout /t 3 >nul
startup.bat

echo ========================================
echo =   Déploiement terminé !
echo =   Accédez à http://localhost:8080/%WAR_NAME%/
echo ========================================
pause
