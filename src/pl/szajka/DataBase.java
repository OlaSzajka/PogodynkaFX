package pl.szajka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBase {
	public static final String DB_DRIVER = "org.sqlite.JDBC";
	public static final String  DB_URL = "jdbc:sqlite:PogodynkaDB.db";
	private Connection conn;
	private Statement stmt;
	
	public DataBase() {
		try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
	}
	
	public ObservableList<String> getCountryNameList() {
		ObservableList<String> country = FXCollections.observableArrayList();
        try {
            ResultSet result = stmt.executeQuery("SELECT name FROM COUNTRY");
            String name;
            while(result.next()) {
                name = result.getString("name");
                country.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return country;
	}
}
