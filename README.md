## Codenvy Postgresql factory with Datasource plugin

# Run the docker image

    Menu Run > Run with > datasource

In the Runner console view it should display

    [STDOUT] Connected to jdbc:postgresql://localhost:5432/testdb_ohcheged with user: codenvy, password: nairashe
    
And also the url should be display in the runner toolbar:

    Application: http://runner1.nightly.codenvy-stg.com:52167


Create a new Datasource connection:

 - `Menu Datasource > New Datasource Connection`
 - Give a name
 - Choose `Hosted database > Postgresql`
 - Fill the generated database name, in our example it is `testdb_ohcheged` (from the runner message)
 - Fill the username, in our example it is `codenvy` (from the runner message)
 - Fill the password, in our example it is `neirashe` (from the runner message)
 - Fill the port, in our example it is `52161` (from the url)
 - Fill the hostname, in our example it is `runner1.nightly.codenvy-stg.com` (from the url)

Test connection should work
after creation, we can browse in the database with the datasource explorer and have completion in sql files, try out test.sql