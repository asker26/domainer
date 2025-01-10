@echo off
setlocal EnableDelayedExpansion

:: Set the installation directory in the user's home directory
set "INSTALL_DIR=%USERPROFILE%\domainer"

echo Creating installation directory...
if not exist "%INSTALL_DIR%" mkdir "%INSTALL_DIR%"

echo Copying files to installation directory...
:: Using robocopy for better copy functionality
:: /E - Copy subdirectories, including empty ones
:: /PURGE - Delete destination files/dirs that no longer exist in source
:: /NFL - No file list
:: /NDL - No directory list
robocopy %~dp0 %INSTALL_DIR% /E /PURGE /NFL /NDL /XD .git .idea

echo Building the Go project...
cd /d "%INSTALL_DIR%"
go build -o domainer.exe

:: Add to PATH permanently through registry
echo Updating PATH in registry...
for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v PATH') do set "CURRENT_PATH=%%b"
if not "!CURRENT_PATH!" == "" (
    echo Current PATH: !CURRENT_PATH!
    echo.
    :: Check if path already exists in PATH
    echo !CURRENT_PATH! | find /i "%INSTALL_DIR%" > nul
    if errorlevel 1 (
        :: Add path only if it doesn't exist
        reg add "HKCU\Environment" /v PATH /t REG_EXPAND_SZ /d "!CURRENT_PATH!;%INSTALL_DIR%" /f
        echo Added to PATH: %INSTALL_DIR%
    ) else (
        echo Path already exists in PATH
    )
) else (
    :: No PATH exists, create new one
    reg add "HKCU\Environment" /v PATH /t REG_EXPAND_SZ /d "%INSTALL_DIR%" /f
    echo Created new PATH with: %INSTALL_DIR%
)

:: Broadcast WM_SETTINGCHANGE message to notify applications of environment changes
powershell -command "$source = @'
using System.Runtime.InteropServices;
public class Win32 {
    [DllImport(\"user32.dll\", SetLastError = true, CharSet = CharSet.Auto)]
    public static extern IntPtr SendMessageTimeout(IntPtr hWnd, uint Msg, UIntPtr wParam, string lParam, uint fuFlags, uint uTimeout, out UIntPtr lpdwResult);
}
'@
Add-Type -TypeDefinition $source -Language CSharp
$HWND_BROADCAST = [IntPtr]0xffff;
$WM_SETTINGCHANGE = 0x1a;
$result = [UIntPtr]::Zero
[Win32]::SendMessageTimeout($HWND_BROADCAST, $WM_SETTINGCHANGE, [UIntPtr]::Zero, 'Environment', 2, 5000, [ref]$result)"

echo.
echo Installation complete! Please follow these steps:
echo 1. Close all open command prompts
echo 2. Open a new command prompt
echo 3. You can then use 'domainer' from any directory
echo.
echo Press any key to exit...
pause
