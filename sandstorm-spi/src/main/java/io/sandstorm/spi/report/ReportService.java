package io.sandstorm.spi.report;

public interface ReportService {

    /**
     * Generates test report for the given execution of a test job.
     *
     * @param jobExecId the identifier of execution of a test job
     * @return the full url of the generated report
     * @throws ReportException if any exception occurred while generating the report.
     */
    String generateReport(String jobExecId);

    /**
     * Deletes test report for the given execution of a test job.
     * @param jobExecId the identifier of execution of a test job
     */
    void deleteReport(String jobExecId);

}
