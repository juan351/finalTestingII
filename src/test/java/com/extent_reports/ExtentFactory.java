package com.extent_reports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance(){
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("Selenoium Version", "4.9.1");
        extent.setSystemInfo("SO", "Windows 10");
        return extent;
    }
}
