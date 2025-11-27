package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.FxAnchorPaneUtil;
import javafx.util.FxButtonUtil;
import utill.FxmlPath;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button dashBordBtn;

    @FXML
    private Button invoiceBtn;

    @FXML
    private Button customerBtn;

    @FXML
    private Button refBtn;

    @FXML
    private Button routeBtn;

    @FXML
    private Button expensesBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button settingBtn;

    @FXML
    private AnchorPane pageLoadingSpace;

    @FXML
    private Region loadingRegionPane;

    @FXML
    private ProgressIndicator progressIndicater;

    Button allButtons[];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        createShortCuts();
    }

    private void createShortCuts() {

    }

    @FXML
    void dashBordBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, dashBordBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.DASHBOARD_MAIN, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void invoiceBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, invoiceBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, customerBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void refBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, refBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void routeBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, routeBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void expensesBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, expensesBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void reportBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, reportBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void settingBtnOnAction(ActionEvent event) {
        FxButtonUtil.setSelectedBtn(allButtons, settingBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void init() {
        allButtons = new Button[]{dashBordBtn, invoiceBtn, customerBtn, refBtn, routeBtn, expensesBtn, reportBtn,
                settingBtn};
    }
    //------------------------------------------------------------------------------------------------------------------
}
