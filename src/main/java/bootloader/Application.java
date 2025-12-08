package bootloader;

import com.property.PropertyReader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.log4j.PropertyConfigurator;
import javafx.util.FxAnchorPaneUtil;
import javafx.util.Configuration;
import javafx.util.FxTheme;
import utill.FxmlPath;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utill.JasperCompiler;
import utill.ReportPath;

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
        loadJasper();
        if (true) { //check software status
            try {
                System.out.println(PropertyReader.BUSINESS_NAME);
                AnchorPane rootPane = FxAnchorPaneUtil.setNew(FxmlPath.LOGIN);
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
    }

    //------------------------------------------------------------------------------------------------------------------
    private void loadJasper() {
//        try {
//           JasperReport jr = JasperCompileManager.compileReport(ReportPath.EXPIRE_DATE_LABEL);
//           JasperPrint jp = JasperFillManager.fillReport(jr, null);
//            System.out.println(jp.getName());
//        } catch (Exception e) {
//            System.out.println("jasper load exception" + e);
//        }
        JasperCompiler.compileReport(ReportPath.EXPIRE_DATE_LABEL);
    }

    //------------------------------------------------------------------------------------------------------------------
    private void initializeSystem() {
        PropertyReader.initialize();// Initialize properties
        FxTheme.setTheme(Configuration.LIGHT_THEME);// Set theme
        PropertyConfigurator.configure(PropertyReader.LOG4J_PROPERTY_FILE_PATH);
    }
    //------------------------------------------------------------------------------------------------------------------
}
