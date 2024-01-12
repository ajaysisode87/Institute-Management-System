package com.ajay.InstituteManagementSystemApp.utils;
import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    public static byte[] writeInstitutesToExcel(List<InstituteDTO> institutes) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Institutes");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "Name",
                    "Location",
                    "Contact Information",
                    "Email ID",
                    "Mobile Number"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            int rowNum = 1;
            for (InstituteDTO institute : institutes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(institute.getName());
                row.createCell(1).setCellValue(institute.getLocation());
                row.createCell(2).setCellValue(institute.getContactInformation());
                row.createCell(3).setCellValue(institute.getEmailId());
                row.createCell(4).setCellValue(institute.getMobileNumber());
            }

            // Write to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }
}

