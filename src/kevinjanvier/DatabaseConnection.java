package kevinjanvier;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "demo_db";
        String databaseUser="root";
        String databasePassword="qwartz";
        String url ="jdbc:mysql://localhost:3339/" +databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        return databaseLink;
    }
}
