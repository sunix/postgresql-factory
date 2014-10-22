package com.codenvy.example.postgresql;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        try {
            new Application().run();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL JDBC Driver.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void run() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        final String host = String.format("jdbc:postgresql://localhost:5432/%s", System.getenv("CODENVY_POSTGRESQL_DB"));
        final String user = System.getenv("CODENVY_POSTGRESQL_USER");
        final String pass = System.getenv("CODENVY_POSTGRESQL_PASSWORD");

        System.out.println(String.format("Connected to %s with user: %s, password: %s", host, user, pass));

        try (Connection sqlConnection = DriverManager.getConnection(host, user, pass)) {
            createTable(sqlConnection);
            insertData(sqlConnection);
            readData(sqlConnection);

            updateData(sqlConnection);
            readData(sqlConnection);

            deleteData(sqlConnection);
            readData(sqlConnection);
        }

    }

    private void createTable(Connection sqlConnection) throws SQLException {
        System.out.println("\nCreate new table `test_user`");
        try (PreparedStatement statement = sqlConnection.prepareStatement(getCreateTableSQL())) {
            System.out.println(String.format("Process SQL query:\n%s", statement.toString()));
            statement.execute();
        }
    }

    private String getCreateTableSQL() {
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream("table_test_user.sql"));
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }

        return sb.toString();
    }

    private void insertData(Connection sqlConnection) throws SQLException {
        Random random = new Random();
        System.out.println("\nInsert new rows into table `test_users`");

        final String insertSQL = "INSERT INTO public.test_users (id, userName, firstName, lastName, birthday, email) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        for (int i = 0; i < 5; i++) {
            try (PreparedStatement statement = sqlConnection.prepareStatement(insertSQL)) {
                statement.setString(1, String.format("Codenvy_%d", (random.nextInt(900) + 100)));
                statement.setString(2, String.format("John_%d", (random.nextInt(900) + 100)));
                statement.setString(3, String.format("Doe_%d", (random.nextInt(900) + 100)));
                statement.setDate(4, new Date(System.currentTimeMillis()));
                statement.setString(5, String.format("user%d@site.com", (random.nextInt(900) + 100)));
                System.out.println(String.format("Process SQL query: %s", statement.toString()));
                statement.execute();
            }
        }
    }

    private void readData(Connection sqlConnection) throws SQLException {
        final String selectSQL = "SELECT * FROM public.test_users";
        final String output = "ID: %d, UserName: %s, First Name: %s, Last Name: %s, Birthday: %s, Email: %s";

        System.out.println("\nSelect all rows from `test_users`");

        try (PreparedStatement statement = sqlConnection.prepareStatement(selectSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.toString()));
            System.out.println("Result set:");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(String.format(output,
                                                     resultSet.getInt("id"),
                                                     resultSet.getString("userName"),
                                                     resultSet.getString("firstName"),
                                                     resultSet.getString("lastName"),
                                                     resultSet.getDate("birthday").toString(),
                                                     resultSet.getString("email")));
                }
            }
        }
    }

    private void updateData(Connection sqlConnection) throws SQLException {
        final String updateSQL = "UPDATE public.test_users SET firstName='Michael', lastName='Smith' WHERE id=3";

        System.out.println("\nUpdate row in table `test_user`");

        try (PreparedStatement statement = sqlConnection.prepareStatement(updateSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.toString()));
            statement.execute();
        }
    }

    private void deleteData(Connection sqlConnection) throws SQLException {
        final String deleteSQL = "DELETE FROM public.test_users WHERE id=3";

        System.out.println("\nDelete row from table `test_user`");

        try (PreparedStatement statement = sqlConnection.prepareStatement(deleteSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.toString()));
            statement.execute();
        }
    }
}