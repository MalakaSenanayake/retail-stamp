package controller;

import common.java.util.Print;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.*;
import utill.FxmlPath;
import utill.ReportPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PrintController implements Initializable {

    @FXML
    private AnchorPane loginMainAnchorPane;

    private static Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void printBtnOnAction(ActionEvent event) {
        print();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void print() {
        printSingleLabel();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void printSingleLabel() {
        InputStream jasperStream = null;
        try {
            jasperStream = Files.newInputStream(Paths.get(ReportPath.EXPIRE_DATE_LABEL + ".jasper"));

            Map<String, Object> parameters = new HashMap<>();
            // Add your two String parameters
            // Second String value
            parameters.put("MFD", "MFD 2025-10-25");      // First String value
            parameters.put("EXP", "EXP 2025-12-25");
            parameters.put("PRICE", "Rs.1900.00");

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperStream,
                    parameters,
                    new JREmptyDataSource());
            boolean printDialog = true; // Show print dialog
            JasperPrintManager.printReport(jasperPrint, printDialog);
            Print.info("Printing completed!");
        } catch (Exception e) {
            Print.error(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void viewUserMainPage() {
        primaryStage = new Stage();
        AnchorPane rootpane = new AnchorPane();
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(FxmlPath.HOME);
            rootpane = loder.load();
            Scene sc = new Scene(rootpane);
            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Theme 1 @ creative IT"); // Application name
            primaryStage.getIcons().add(new Image("/image/appIcon.png"));
            primaryStage.setScene(sc);
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    System.out.println("closed");
                }

            });
            primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    System.out.println("minimize window");
                }
            });
        } catch (IOException ex) {
            System.err.println("LoginController - loadAdminMain" + ex);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void logOutSystem() {
        System.out.println("logOutSystem");
    }

    //------------------------------------------------------------------------------------------------------------------
    public static Stage getPrimaryStage() {
        return primaryStage; // Getter method for primaryStage
    }
}
