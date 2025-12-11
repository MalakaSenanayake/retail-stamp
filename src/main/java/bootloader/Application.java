package bootloader;

import com.property.PropertyReader;
import javafx.util.Configuration;
import org.apache.log4j.PropertyConfigurator;
import javafx.util.FxAnchorPaneUtil;
import utill.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author malaka senanayake
 */
public class Application extends javafx.application.Application {

    /**
     * @param args the command line arguments
     */
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        launch(args);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage primaryStage) {
        initializeSystem();
        try {
            System.out.println(PropertyReader.BUSINESS_NAME);
            AnchorPane rootPane = FxAnchorPaneUtil.setNew(Path.PRINT_PAGE);
            Scene sc = new Scene(rootPane);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.getIcons().add(new Image("/image/appIcon.png"));
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    private void initializeSystem() {
        PropertyReader.initialize();// Initialize properties
        PropertyConfigurator.configure(PropertyReader.LOG4J_PROPERTY_FILE_PATH);
        Configuration.setAppIcon("/image/appIcon.png");
        Configuration.setAlertTitle("retail-stamp");
    }
    //------------------------------------------------------------------------------------------------------------------
}
