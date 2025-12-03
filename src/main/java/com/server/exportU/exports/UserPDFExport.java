package com.server.exportU.exports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.server.exportU.dto.UserDto;

public class UserPDFExport {

    private final List<UserDto> userDtoList;

    public UserPDFExport(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Prenom(s)", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nom", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (UserDto user : userDtoList) {
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getEmail());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(16);
        font.setColor(Color.ORANGE);

        Paragraph paragraph = new Paragraph("Users", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
