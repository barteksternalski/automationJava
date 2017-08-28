package helpers;
import java.sql.*;

// to load JDBC driver
// mvn install:install-file -Dfile=.\src\main\resources\jdbcDriver\sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

public class dbConnection {

    Connection connectToDB(String sDBName, String sDBPath, String sUser, String sPass) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://" + sDBPath + ":1433;DatabaseName="+sDBName;
        return DriverManager.getConnection(url, sUser, sPass);
    }

    public ResultSet executeQuery(String sQuery, String sDBName, String sDBPath, String sUser, String sPass) throws Exception {
        Statement stmt = connectToDB(sDBName, sDBPath, sUser, sPass).createStatement();
        return stmt.executeQuery(sQuery);
    }

    public void printQueryData(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (i > 1) System.out.print("\t");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
        }
    }

    public String getActivationToken(ResultSet rs, String sCol) throws Exception {
        String sTemp = "";
        while(rs.next()) {
            sTemp = rs.getString(sCol);
        }
        return sTemp;
    }

}
