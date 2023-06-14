package com.codegym.c11.service.sf.pdf;

import com.codegym.c11.model.dto.Ticket.TicketRequestDtoTest;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    public byte[] export(TicketRequestDtoTest ticket) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();

        // Set up fonts
        BaseFont baseFont = BaseFont.createFont("Helvetica", BaseFont.CP1250, BaseFont.EMBEDDED);
        Font fontTitle = new Font(baseFont, 20, Font.BOLD, Color.BLACK);
        Font fontHeading = new Font(baseFont, 12, Font.BOLD, Color.DARK_GRAY);
        Font fontBody = new Font(baseFont, 12, Font.NORMAL, Color.BLACK);

        // Add title
        Paragraph titleParagraph = new Paragraph("Ticket Information", fontTitle);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        titleParagraph.setSpacingAfter(20);
        document.add(titleParagraph);

        // Add information paragraphs
        addTicketInfoParagraph(document, "Ticket Code", ticket.getTicketCode(), fontHeading, fontBody);
        addTicketInfoParagraph(document, "Movie Title", ticket.getMovieTitle(), fontHeading, fontBody);
        addTicketInfoParagraph(document, "Cinema", ticket.getCinema(), fontHeading, fontBody);
        addTicketInfoParagraph(document, "Screening Room", ticket.getScreeningRoom(), fontHeading, fontBody);
        addTicketInfoParagraph(document, "Showtime", ticket.getShowTime(), fontHeading, fontBody);
        addTicketInfoParagraph(document, "Seats", ticket.getSeats(), fontHeading, fontBody);

        // Add cinema information with background color
        Rectangle backgroundRect = new Rectangle(0, 0, PageSize.A4.getWidth(), 150);
        backgroundRect.setBackgroundColor(new CMYKColor(1, 1, 0, 0)); // Set your desired background color
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.rectangle(backgroundRect);

        // Add cinema details
        Font fontCinema = new Font(baseFont, 16, Font.BOLD, Color.WHITE);
        Paragraph cinemaParagraph = new Paragraph("CG Cinema\n\n" +
                "Tầng 4, 21K Nguyễn Văn Trỗi, Phường 10, Phú Nhuận, TP.HCM\n\n" +
                "Email hỗ trợ: cg.cinema11@gmail.com\n\n" +
                "Hotline: 1900 6969", fontCinema);
        cinemaParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(cinemaParagraph);

        document.close();

        return outputStream.toByteArray();
    }

    private void addTicketInfoParagraph(Document document, String heading, String content, Font fontHeading, Font fontBody) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        Chunk chunkHeading = new Chunk(heading + ": ", fontHeading);
        Chunk chunkContent = new Chunk(content, fontBody);

        paragraph.add(chunkHeading);
        paragraph.add(chunkContent);
        paragraph.setSpacingAfter(10);

        document.add(paragraph);
    }

}
