package io.sandstorm.spi.impl;

import io.sandstorm.spi.report.ReportService;

public class DefaultReportService implements ReportService {


    @Override
    public String generateReport(String jobExecId) {
        return "";
    }

    @Override
    public void deleteReport(String jobExecId) {
    }

}
