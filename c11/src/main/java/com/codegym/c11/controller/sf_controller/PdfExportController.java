package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.service.PDFGeneratorService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api/test/pdf")
public class PdfExportController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @GetMapping
    public ResponseEntity<?> generatePDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currendDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currendDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
