Book_Shop_Management_System

Overview:-
This Java application is designed to manage a bookstore's inventory and sales information. 
It provides functionalities for adding, updating, and deleting books from inventory, as well as recording customer purchases and generating sales reports.

Features:-
Dashboard: View summary statistics such as the total number of available books, total income, and total number of customers.
Available Books Management: Add, update, and delete books from the inventory. Each book entry includes details such as book ID, title, author, genre, publication date, price, and image.
Purchase Management: Record customer purchases, including book details, quantity, and total price.
User Interface: The application features a user-friendly graphical interface built using JavaFX.

How to Use:-
Setup: Ensure you have Java installed on your system.
Database Configuration: Configure the database connection settings in the database.java file.
Run the Application: Compile and run the dashboardController.java file to launch the application.
Navigation: Use the navigation buttons to switch between different functionalities (Dashboard, Available Books, Purchase).
Available Books Management: Add, update, or delete books using the respective buttons. Images for books can be imported from file.
Purchase Management: Record customer purchases by selecting the book, specifying quantity, and clicking on the 'Add' button. Once all purchases are added, click on 'Pay' to finalize the transactions.
Logout: To logout from the application, click on the 'Logout' button.

Dependencies:-
JavaFX: For building the graphical user interface.
MySQL Connector/J: For connecting the application to a MySQL database.
