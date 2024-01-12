package com.ajay.InstituteManagementSystemApp.utils;
import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import com.opencsv.CSVWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvUtils {
    public static byte[] writeInstitutesToCsv(List<InstituteDTO> institutes) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (CSVWriter writer =
                     new CSVWriter
                             (new OutputStreamWriter(outputStream))) {
            // Write header
            writer.writeNext(new String[]{"Name",
                    "Location",
                    "Contact Information",
                    "Email ID",
                    "Mobile Number"});

            // Write data
            for (InstituteDTO institute : institutes) {
                writer.writeNext(new String[]{
                        institute.getName(),
                        institute.getLocation(),
                        institute.getContactInformation(),
                        institute.getEmailId(),
                        institute.getMobileNumber()});
            }
        }
        return outputStream.toByteArray();
    }
}

