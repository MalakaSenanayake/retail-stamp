package utill;

import common.java.util.Print;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperCompiler {
    //------------------------------------------------------------------------------------------------------------------
    public static void compileReport(String jrxmlFilePath, String jasperFilePath) {
        try {
            // Compile JRXML to Jasper
            JasperCompileManager.compileReportToFile(jrxmlFilePath, jasperFilePath);
            Print.info(jrxmlFilePath +"- Report compiled to Jasper file successfully!");
        } catch (JRException e) {
            Print.error(e.getMessage());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
