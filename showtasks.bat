call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startapp
echo.
echo The application cannot be started
goto fail

:fail
echo.
echo There were errors

:startapp
start brave "http://localhost:8080/crud/v1/task/tasks"
timeout 5
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There is a problem with the application
goto fail

:end
echo.
echo Work is finished.