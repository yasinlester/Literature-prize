Literature Prize Information System
This Java program is designed to read and display information about literature prize winners from a data file. It provides a console-based interface for users to interact with the data.
Features

Data Loading: Reads literature prize data from a file named literature-prizes.txt.
Object-Oriented Design: Implements LiteraturePrize and Laureate classes to represent the data.
Console Menu: Offers a user-friendly interface with the following options:

List prize winners for a specified year range
Select and display detailed information for a specific year
Search for laureates by writing genre
Exit the program



Requirements

Java 8
NetBeans IDE (configured for Ant build)

Setup and Running

Clone this repository or download the source code.
Open the project in NetBeans IDE.
Ensure the literature-prizes.txt file is located in the project's root folder.
Build and run the project.

Usage
Upon running the program, you will be presented with a menu:


----------------------
Literature prize menu
----------------------
List ................1
Select ..............2
Search ..............3
Exit.................0
----------------------
Enter choice >



Choose option 1 to list prize winners for a specific year range.
Choose option 2 to view detailed information about prize winners for a specific year.
Choose option 3 to search for laureates by writing genre.
Choose option 0 to exit the program.

Important Notes

The program is designed to work with Java 8 and NetBeans Ant build configuration.
Ensure that the literature-prizes.txt file is not modified, as it is considered a read-only data source.
The program creates a collection of objects in memory upon startup and does not access the file again during runtime.

