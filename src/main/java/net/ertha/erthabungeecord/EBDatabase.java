package net.ertha.erthabungeecord;


import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class EBDatabase {

    private ErthaBungeecord eb;

    public EBDatabase(ErthaBungeecord erthaBungeecord){
        eb = erthaBungeecord;
    }

    public HikariDataSource connect(){
        HikariDataSource hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(EBSettings.databaseMaxPoolSize);
        hikari.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
        hikari.addDataSourceProperty("serverName", EBSettings.database_address);
        hikari.addDataSourceProperty("port", EBSettings.databasePort);
        hikari.addDataSourceProperty("databaseName", EBSettings.databaseName);
        hikari.addDataSourceProperty("password", EBSettings.databasePassword);
        return hikari;
    }

    public PreparedStatement newStatement(String statement){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = eb.hikari.getConnection();
            preparedStatement = connection.prepareStatement(statement);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return preparedStatement;

    }

    public void endConnection(Connection connection){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void endPreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void configureDatabase(){
        createHeadsTable();
    }

    private void createHeadsTable(){
        String statement = "CREATE TABLE IF NOT EXIST" +
                "UUID"

        PreparedStatement preparedStatement = newStatement()

    }
}
