package reusable;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends WebDriverFactory implements ITestListener {
    ExtentReports reports = getReportObjects();
    ExtentTest test;
    static String reportPath;
    static String REPORT_NAME = "Report_";

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result){
        test = reports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result){
        extentTest.get().log(Status.PASS,"Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result){
        extentTest.get().fail(result.getThrowable());
        String path = result.getMethod().getMethodName();
        extentTest.get().addScreenCaptureFromPath(getScreenshot(path,REPORT_NAME), path);
//        String throwableMessage = getFullStackTrace(result.getThrowable());
//        writeToFileAndOpenWithNotePad(throwableMessage);
    }

    @Override
    public void onTestSkipped(ITestResult result){
        extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context){
        reports.flush();
    }

    public static ExtentReports getReportObjects(){
        final String REPORTS_DIR = System.getProperty("user.dir") + File.separator + "results";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh-mm-ss_a");
        String timestamp = dateFormat.format(new Date());
        REPORT_NAME = REPORTS_DIR + File.separator + REPORT_NAME + timestamp;
        reportPath = REPORT_NAME + File.separator + "index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Report_" + timestamp);
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester","Satya Prakash");
        return extentReports;
    }

    private String getFullStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    private void writeToFileAndOpenWithNotePad(String throwableMessage){
        File reportsDir = new File(System.getProperty("user.dir") + "//reports");
        File file = new File(reportsDir,"ErrorLog.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(throwableMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Runtime.getRuntime().exec("notepad.exe " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
