psr.exe /stop
REM ****Set up Logging ****
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c_%%a_%%b)
For /f "tokens=1-2 delims=/:" %%a in ("%TIME%") do (set mytime=%%a_%%b)
set mytime=%mytime: =0%

psr.exe /start /output C:\UTS\PSR\%mydate%_%mytime%.zip /sc 1 /gui 0 /maxsc 100