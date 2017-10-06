# Sectors-form

Java EE web application which opens a form that, when submitted, inserts the data into the formsubmissions table in the database, pulls the same data and refills the form. After this, the user can make changes and resubmit the form to update their entry in the database.
The multiple select box in the form is filled dynamically with data from the formoptions table in the database.

Used IDE: IntelliJ IDEA 2017.2.5 Ultimate.
Used database: PostgreSQL 9.6.2.
Used localhost server: GlassFish 4.1.2 (needs to be set up in C:\ to work with default configuration).
PostgreSQL database commands are located in forminfodb_dump.txt.
