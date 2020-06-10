package com.book.model.service.impl;

import com.book.model.presentation.Book;
import com.book.model.service.Report;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class ReportPDF implements Report {

    private static String PDF_FILE = "reports/PDF/Report.pdf";

    @Override
    public void generateReport(List<Book> books) {
        try{

            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(PDF_FILE));

            document.open();
            document.add(new Paragraph("~ BOOKS OUT OF STOCK:"));
            document.add(new Paragraph(" "));

            books.forEach(book -> {
                try {
                    document.add(new Paragraph("Title: " + book.getTitle()));
                    document.add(new Paragraph(" "));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });

            document.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
