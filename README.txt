REQUIREMENTS:
-MYSQL server and MYSQL workbench are installed and working.
-JAVA JRE or/and JDK are installed.
-You know the location, login and password of your MYSQL server.

FILES ATTACHED:
-"AxelVanHoyweghen_FullDB.sql": SQL files that contain the complete database.
-Folder "Seperate DB Files": Contain the seperate sql files(not needed).
-Folder "AirAxel": Contains the java application and required api's and files.
-"Axel_Van_Hoyweghen_Paper.PDF": The project report.
-"Axel_van_hoyweghen_Presentation.pptx": The project presentation.

TESTED:
The application was tested on the machine it was created on(Windows 10) and on a computer(Windows 10) that did not had MySQL Workbench and server or Java JRE and JDK installed. These where first installed and than the programm was initialized and executed as in the steps below. 

How to:
Initliaze Database:
1) Open MySQL workbench 
2) Select and open the MySQL connection you want to use.
3) Go to the navigator and click Data import/Restore.
4) Select Import from Self-Contained File.
5) Select the file "AxelVanHoyweghen_FullDB.sql" in this folder.
6) Do not select anything else and click start import. 
7) Wait untill the import has completed and exit MySQL Workbench.

Run program:
1) Open the AirAxel folder contained in this folder.
2) Click AirAxel.exe to start the application. 
3) Enjoy the application!!!

Additional information:
Flights can be booked however you want, seperate flight or combinations as stated below. Not all flights fly every day, therefore not every combination exist on any day.
The following flight combinations exist (every line can also be booked as a flight):

DTW -> AMS
AMS-> BRU

BRU -> AMS
AMS -> DTW

BRU -> ATL
ATL -> LAX
LAX -> MEX

DTW -> CDG

CDG -> DTW

ATL -> DTW
DTW -> PEK

AMS -> DTW
DTW -> CDG
