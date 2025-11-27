package connector;

import com.property.PropertyReader;
import org.apache.log4j.Logger;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class MySqlConnector {
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "1314246";
    private static final String DATABASE_NAME = "oms_2.0";
    private static final String URL = "jdbc:mysql://" + PropertyReader.DATABASE_SERVER_IP + ":"
            + PropertyReader.DATABASE_SERVER_PORT + "/" + DATABASE_NAME;

    private static Connection connection = null;
    private static final Logger logger = Logger.getLogger(MySqlConnector.class);

    //Create connection.
    public static synchronized Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
            logger.info("Connection established.");
            return connection;

        }
        return connection;
    }
    // set parameters to.
    public static void setParameters(PreparedStatement preparedStatement, Object... params) {
        int startIndex = 1;
        for (int i = 0; i < params.length; i++) {
            try {
                preparedStatement.setObject(startIndex + i, params[i]);
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
                logger.error(exception);
            }
        }
    }
    //get backups without path
    public static boolean getBackup() {
        boolean flag = false;
        try {
            String backupRootFolder = PropertyReader.DATABASE_DEFAULT_BACKUP_PATH;
            File fileFolder = new File(backupRootFolder + "\\" + getFolderName() + "");// Create folder
            fileFolder.mkdir();
            String backupFilePath = fileFolder + "\\" + getSQlBackupFileName();
            String executeCommand = "" + PropertyReader.DATABASE_SQL_DUMP_PATH + " -u" + DB_USER_NAME + " -p"
                    + DB_PASSWORD + " --add-drop-database -B " + DATABASE_NAME +
                    " -r" + backupFilePath + "";
            Runtime.getRuntime().exec(executeCommand);
            flag = true;
        } catch (Exception e) {
            flag = false;
            System.out.println("get backups" + e);
        }
        return flag;
    }

    //get backups with path
    public static boolean getBackup(String path) {
        boolean flag = false;
        try {
            File fileFolder = new File(path + "\\" + getFolderName() + "");// Create folder
            fileFolder.mkdir();
            String backupFilePath = fileFolder + "\\" + getSQlBackupFileName();
            String executeCommand = "" + PropertyReader.DATABASE_SQL_DUMP_PATH + " -u" + DB_USER_NAME + " -p"
                    + DB_PASSWORD + " --add-drop-database -B " + DATABASE_NAME +
                    " -r" + backupFilePath + "";
            Runtime.getRuntime().exec(executeCommand);
            flag = true;
        } catch (Exception e) {
            System.out.println("get backups" + e);
        }
        return flag;
    }
    //Helper methods for get backups
    private static String getFolderName() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        return sdf.format(d);
    }
    //Helper methods for get backups
    private static String getSQlBackupFileName() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(d);
        return fileName + "-db.sql";
    }
}
