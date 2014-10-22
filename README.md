# Codenvy Postgresql Factory with Datasource Plugin

## Run the Docker image

    Menu Run > Run with > Datasource

In the Runner console view it should display

    [STDOUT] export CODENVY_POSTGRESQL_PASSWORD=nairashe
    [STDOUT] export CODENVY_POSTGRESQL_DB=testdb_ohcheged
    [STDOUT] export CODENVY_POSTGRESQL_USER=codenvy
    [STDOUT] port is mapped on 62126 on host runner1.nightly.codenvy-stg.com


## Create a new Datasource connection:

 - Go to `Datasource > New Datasource Connection`
 - Give a name, for instance `datasource`
 - Choose `Hosted Database > PostgreSQL`
 - Fill the database name, in our example it is `testdb_ohcheged`
 - Fill the host name, in our example it is `runner1.nightly.codenvy-stg.com`
 - Fill the port, in our example it is `52161`
 - Fill the username, in our example it is `codenvy`
 - Fill the password, in our example it is `neirashe`


## Test, browse and auto complete

Test connection should work
after creation, we can browse in the database with the datasource explorer and have completion in sql files, try out test.sql
