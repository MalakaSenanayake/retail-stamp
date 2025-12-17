package bootloader;

import com.property.PropertyReader;
import common.java.util.Print;
import controller.SplashScreenController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Configuration;
import javafx.util.FxAnchorPaneUtil;
import javafx.util.FxGroupPaneUtil;
import javafx.util.FxStackPaneUtil;
import org.apache.log4j.PropertyConfigurator;
import utill.JasperCompiler;
import utill.Path;

/**
 * @author malaka senanayake
 */
public class Application extends javafx.application.Application {

    /**
     * @param args the command line arguments
     */

    private Stage splashStage;
    private SplashScreenController splashScreenController;

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeSystem();
        showSplashScreen();
        initializeApplication(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //------------------------------------------------------------------------------------------------------------------
    private void initializeApplication(Stage primaryStage) {
        Task<Void> initTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Update status
                updateMessage("Loading configuration...");
                updateProgress(0.1, 1.0);
                Thread.sleep(500); // Simulate work

                // Load Jasper Reports
                updateMessage("Loading Jasper Reports...");
                updateProgress(0.3, 1.0);
                loadJasperReports();

                // Initialize database connection
                updateMessage("Connecting to database...");
                updateProgress(0.5, 1.0);
                initializeDatabase();
                Thread.sleep(1000);

                // Load other resources
                updateMessage("Loading resources...");
                updateProgress(0.7, 1.0);
                loadResources();
                Thread.sleep(1000);

                // Finalizing
                updateMessage("Finalizing...");
                updateProgress(0.9, 1.0);
                Thread.sleep(1000);

                updateMessage("Ready!");
                updateProgress(1.0, 1.0);

                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    splashStage.close();
                    showLabelPrintView(primaryStage);
                });
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    splashScreenController.updateStatus("Error during initialization!");
                    splashScreenController.updateProgress(0);
                    // You might want to add a retry button or error dialog here
                });
            }
        };
        splashScreenController.bindToTask(initTask);
        // Start the initialization task
        new Thread(initTask).start();
    }

    // Show Splash screen ----------------------------------------------------------------------------------------------
    private void showSplashScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(Path.SPLASH_SCREEN_VIEW);
        Parent root = loader.load();
        splashScreenController = loader.getController();
        splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.setScene(new Scene(root));
        splashStage.getIcons().add(new Image(Path.APP_ICON));
        splashStage.show();
        splashStage.centerOnScreen();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void showLabelPrintView(Stage primaryStage) {
        try {
            StackPane rootPane = FxStackPaneUtil.setNew(Path.LABEL_PRINT_VIEW);
            Scene sc = new Scene(rootPane);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(Path.APP_ICON));
            primaryStage.setTitle(Configuration.getAppName());
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (Exception e) {
            Print.error(e.getMessage());
        }
    }

    //-Load jasper------------------------------------------------------------------------------------------------------
    private void loadJasperReports() throws Exception {
        JasperCompiler.compileReport(Path.RETAIL_LABEL_REPORT);
        Print.info("Loading Jasper Reports...");
    }

    //------------------------------------------------------------------------------------------------------------------
    private void initializeDatabase() {
        Print.info("Initializing Database...");
    }

    //------------------------------------------------------------------------------------------------------------------
    private void loadResources() {
        Print.info("Loading resources...");
        // We can check crack status here.
    }

    //------------------------------------------------------------------------------------------------------------------
    private void initializeSystem() {
        PropertyReader.initialize();// Initialize properties
        PropertyConfigurator.configure(PropertyReader.LOG4J_PROPERTY_FILE_PATH);
        Configuration.setAppIcon(Path.APP_ICON);
        Configuration.setAppName(PropertyReader.APP_NAME+" "+PropertyReader.SOFTWARE_VERSION);
    }
    //------------------------------------------------------------------------------------------------------------------
}
