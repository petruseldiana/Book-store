package com.book.model.service.impl;

import com.book.model.service.Report;

public class ReportFactory {

    public Report getReport(String reportType) {

        switch(reportType) {
            case "TXT":
                return new ReportTXT();
            case "PDF":
                return new ReportPDF();
        }
        return null;
    }
}
