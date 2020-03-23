Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
choco install urlrewrite -y 
Install-WindowsFeature NET-Framework-45-ASPNET 
Install-WindowsFeature Web-Asp-Net45
icacls C:\inetpub\wwwroot\ /grant "IIS_IUSRS:(OI)(CI)F"