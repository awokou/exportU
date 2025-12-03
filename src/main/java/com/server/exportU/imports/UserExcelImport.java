package com.server.exportU.imports;

import com.server.exportU.entity.User;
import com.server.exportU.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.util.Iterator;

@Component
public class UserExcelImport {

    private final UserRepository userRepository;

    public UserExcelImport(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /* Import */
    public void importExcel(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < CountRowExcel(sheet.rowIterator()); i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue; // skip blank rows

            String firstName = getCellValue(row, 0);
            String lastName  = getCellValue(row, 1);
            String email     = getCellValue(row, 2);

            // Skip rows without meaningful data
            if (firstName.isBlank() && lastName.isBlank() && email.isBlank()) {
                continue;
            }

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            userRepository.save(user);
        }

        workbook.close();
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);

        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getRichStringCellValue().getString();
            case BLANK -> "";
            default -> "";
        };
    }



    /* Cout Row of Excel Table */
    public static int CountRowExcel(Iterator<Row> iterator) {
        int size = 0;
        while (iterator.hasNext()) {
            Row row = iterator.next();
            size++;
        }
        return size;
    }
}
