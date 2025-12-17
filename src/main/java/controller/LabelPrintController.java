package controller;

import common.java.util.NumberUtil;
import common.java.util.Print;
import dto.PriceDateDto;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.FxAlertsUtil;
import javafx.util.FxDatePickerUtil;
import javafx.util.FxTextFieldUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import utill.JasperCompiler;
import utill.Path;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LabelPrintController implements Initializable {

    @FXML
    private AnchorPane loginMainAnchorPane;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private VBox progressPane;
    @FXML
    private DatePicker mfdDp;
    @FXML
    private TextField mfdPreFixTf;
    @FXML
    private DatePicker expDp;
    @FXML
    private TextField expPreFixTf;
    @FXML
    private TextField prizeTf;
    @FXML
    private TextField pricePreFixTf;
    @FXML
    private TextField expireMonthTf;
    @FXML
    private ComboBox<Integer> lblCountCb;
    @FXML
    private CheckBox printDialogCb;

    private static Stage primaryStage;
    private TextField[] allTf;
    String mfdPrefix;
    String mfDate;
    String expPrefix;
    String expDate;
    String pricePreFix;
    String price;
    int labelCount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @FXML
    void printBtnOnAction(ActionEvent event) {
        confirmDetails();
    }

    @FXML
    void calculateDateBtnOnAction(ActionEvent event) {
        calculateExpireDate();
    }

    @FXML
    void expireMonthTfKeyReleased(KeyEvent event) {
        calculateExpireDate();
    }

    @FXML
    void mfdDpOnAction(ActionEvent event) {
        calculateExpireDate();
    }

    //------------------------------------------------------------------------------------------------------------------

    private void showLoading(boolean visible) {
        progressPane.setVisible(visible);
        progressIndicator.setVisible(visible);
    }

    //------------------------------------------------------------------------------------------------------------------
    private void init() {
        allTf = new TextField[]{mfdPreFixTf, expireMonthTf, expPreFixTf, pricePreFixTf, prizeTf};

        FxDatePickerUtil.toFormatWithToday(mfdDp);
        FxDatePickerUtil.toFormat(expDp);

        FxTextFieldUtil.toOnlyDecimalPositive(prizeTf);
        FxTextFieldUtil.toOnlyDecimalPositive(expireMonthTf);
        FxTextFieldUtil.toOnlyInt(expireMonthTf);

        mfdDp.setEditable(false);
        expDp.setEditable(false);
        lblCountCb.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
       // lblCountCb.getItems().addAll(1, 2, 3, 4, 5);
        lblCountCb.getSelectionModel().selectFirst();
        toDefault();

    }

    //------------------------------------------------------------------------------------------------------------------
    private void toDefault() {
        showLoading(false);
        expireMonthTf.setText("9");
        mfdPreFixTf.setText("MFD");
        expPreFixTf.setText("EXPD");
        pricePreFixTf.setText("MRP");
        lblCountCb.getSelectionModel().selectFirst();
        printDialogCb.setSelected(false);
        expDp.setValue(null);
        calculateExpireDate();

    }

    //------------------------------------------------------------------------------------------------------------------
    private void calculateExpireDate() {
        if (FxTextFieldUtil.isNotEmptyWithoutNotification(expireMonthTf)) {
            FxDatePickerUtil.setDatePlusGivenMonths(mfdDp, expDp, Integer.parseInt(expireMonthTf.getText()));
        } else {
            expDp.setValue(null);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    private void confirmDetails() {
        if (FxDatePickerUtil.isNotEmptyDatePicker(expDp)) {
            if (FxTextFieldUtil.isNotEmpty(allTf)) {
                mfdPrefix = mfdPreFixTf.getText();
                mfDate = mfdDp.getValue().toString();
                expPrefix = expPreFixTf.getText();
                expDate = expDp.getValue().toString();
                pricePreFix = pricePreFixTf.getText();
                price = NumberUtil.toCurrencyFormat(prizeTf.getText());
                String msg = mfdPrefix + "  : " + mfDate +
                        "\n" + expPrefix + " : " + expDate +
                        "\n" + pricePreFix + "  : Rs." + price;
                if (FxAlertsUtil.conformationMessage(msg)) {
                    startPrintTask();
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    private void startPrintTask() {
       showLoading(true);
        Task<Void> printingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    printLabel();
                });
                return null;
            }
        };

        progressIndicator.progressProperty().bind(printingTask.progressProperty());
        printingTask.setOnSucceeded(event -> {
            showLoading(false);
            Print.info("Print task has been completed!");
        });
        printingTask.setOnFailed(event -> {
            showLoading(false);
            Print.error("Print task has been failed!");
            event.getSource().getException().printStackTrace();
        });
        new Thread(printingTask).start();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void printLabel() {
        InputStream jasperReport = null;
        try {
            jasperReport = Files.newInputStream(Paths.get(Path.RETAIL_LABEL_REPORT_JASPER));
            List<PriceDateDto> priceDateList = new ArrayList<>();
            int lblCount = lblCountCb.getSelectionModel().getSelectedIndex();
            for (int i = 0; i <= lblCount; i++){
                String mfd = mfdPrefix+"  : "+mfDate;
                String exp = expPrefix+": "+expDate;
                String prz = pricePreFix+"  : "+price;
                priceDateList.add(new PriceDateDto(mfd,exp,prz));
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(priceDateList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            boolean printDialog = false; // Show print dialog
            printDialog = printDialogCb.isSelected();
            JasperPrintManager.printReport(jasperPrint, printDialog);
            Print.info("Printing completed!");
        } catch (Exception e) {
            Print.error(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public static Stage getPrimaryStage() {
        return primaryStage; // Getter method for primaryStage
    }
    //------------------------------------------------------------------------------------------------------------------
}
