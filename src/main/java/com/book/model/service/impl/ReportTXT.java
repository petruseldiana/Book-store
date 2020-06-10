package com.book.model.service.impl;

import com.book.model.presentation.Book;
import com.book.model.service.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportTXT implements Report {

    private static String TXT_FILE = "reports/TXT/Report.txt";

    static BufferedWriter writer = null;

    @Override
    public void generateReport(List<Book> books) {
        try {
            writer= new BufferedWriter(new FileWriter(TXT_FILE));
            writer.write("-> BOOKS OUT OF STOCK:");
            writer.newLine();
            writer.newLine();

            books.forEach(book -> {
                try {
                    writer.write("Title: " );
                    writer.write(book.getTitle());
                    writer.newLine();
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
