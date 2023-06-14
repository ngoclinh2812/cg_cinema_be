package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.Ticket.TicketRequestDtoTest;
import com.codegym.c11.service.sf.pdf.PDFGeneratorService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api/test/pdf")
public class PdfExportController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> generatePDF(@RequestBody TicketRequestDtoTest ticketDto) throws DocumentException, IOException {
        try {
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String filename = "pdf_" + currentDateTime + ".pdf";

            byte[] pdfContent = pdfGeneratorService.export(ticketDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (DocumentException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating the PDF document");
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error handling the PDF file");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
