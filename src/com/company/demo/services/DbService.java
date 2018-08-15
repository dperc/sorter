package com.company.demo.services;

import com.company.demo.model.DataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbService {

    private final Logger logger = LoggerFactory.getLogger(DbService.class);

    private final String URL = "jdbc:mysql://localhost/testDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String user = "testuser";
    private final String PASSWORD = "testUser";


    public void initDbTable() {
        String sql = "CREATE TABLE IF NOT EXISTS sortedData( " +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "matchId VARCHAR(255), " +
                "marketId INTEGER, " +
                "outcomeId VARCHAR(255), " +
                "specifiers VARCHAR(255)," +
                "insertionTimestamp VARCHAR(255));";

        try (Connection con = DriverManager.getConnection(URL, user, PASSWORD);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error");
            logger.error("Error creating db table: {}", ex);
        }
    }

    /**
     * Insert DataObjects into database and add insertion timestamp column.
     * @param listOfData - list of DataObjects to be inserted into DB.
     */
    public void insertData(List<DataObject> listOfData) {

        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(URL, user, PASSWORD);
        } catch (SQLException e) {
            logger.error("Database connection failed. {}", e);
        }

        for(DataObject dataObject : listOfData) {
            String sql = "INSERT INTO sortedData(matchId, marketId, outcomeId, specifiers, insertionTimestamp) VALUES(?, ?, ?, ?, UNIX_TIMESTAMP(CURTIME(4)))";

            try (
                 PreparedStatement pst = dbConnection.prepareStatement(sql)) {

                pst.setString(1, dataObject.getMatchId());
                pst.setString(2, dataObject.getMarketId() + "");
                pst.setString(3, dataObject.getOutcomeId());
                pst.setString(4, dataObject.getSpecifiers());
                pst.executeUpdate();

            } catch (SQLException ex) {
                logger.error("Error inserting data: {}  into table.", dataObject.toString(),  ex);
            }
        }
    }
}
