/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utill;

import bootloader.Application;
import java.net.URL;

/**
 *
 * @author Malaka Senanayake
 */
public class FxmlPath {
    public static final URL LOGIN = Application.class.getResource("/view/Print.fxml");
    public static final URL EMPTY= Application.class.getResource("/view/Empty.fxml");
    public static final URL DASHBOARD_MAIN = Application.class.getResource("/view/dashboard/DashboardMain.fxml");
    public static final URL HOME = Application.class.getResource("/view/Home.fxml");
    public static final URL DASHBOARD_ALL_COMPONENTS = Application.class.getResource("/view/dashboard/DashboardAllComponent.fxml");
    public static final URL DASHBOARD_TABLE_DESIGN_1 = Application.class.getResource("/view/dashboard/DashboardTableDesign.fxml");

    //public static final URL LOGIN = Application.class.getResource("/view/Print.fxml");
}
