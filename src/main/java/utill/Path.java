/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utill;

import bootloader.Application;
import com.property.PropertyReader;

import java.net.URL;

/**
 *
 * @author Malaka Senanayake
 */
public interface Path {
    static final String APP_ICON = "/image/appIcon.png";

    public static final URL LABEL_PRINT_VIEW = Application.class.getResource("/view/LabelPrintView.fxml");
    public static final URL SPLASH_SCREEN_VIEW = Application.class.getResource("/view/SplashScreenView.fxml");

    public static final String RETAIL_LABEL_REPORT = PropertyReader.REPORTS_FOLDER_PATH+"/RetailLabelReport";
}
