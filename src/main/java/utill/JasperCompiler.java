package utill;

import common.java.util.Print;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperCompiler {
    //------------------------------------------------------------------------------------------------------------------
    public static void compileReport(String reportFilePath) {
        try {
            // Compile JRXML to Jasper
            JasperCompileManager.compileReportToFile(
                    reportFilePath+".jrxml",
                    reportFilePath+".jasper"
            );
            Print.info(reportFilePath+"- Report compiled successfully!");
        } catch (JRException e) {
            Print.error(e.getMessage());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
