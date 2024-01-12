package com.ajay.InstituteManagementSystemApp.controller;

import com.ajay.InstituteManagementSystemApp.config.AppConstants;
import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import com.ajay.InstituteManagementSystemApp.service.InstituteService;
import com.ajay.InstituteManagementSystemApp.utils.CsvUtils;
import com.ajay.InstituteManagementSystemApp.utils.ExcelUtils;
import com.ajay.InstituteManagementSystemApp.utils.InstituteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/institutes")
public class InstituteController {
    private final InstituteService instituteService;
    @Autowired
    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @PostMapping
    public ResponseEntity<InstituteDTO> registerInstitute(@RequestBody InstituteDTO instituteDTO){
        InstituteDTO createInstitute=this.instituteService.registerInstitute(instituteDTO);
        return new ResponseEntity<> (createInstitute, HttpStatus.CREATED);
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<InstituteDTO> updateInstitute(@RequestBody InstituteDTO instituteDTO,@PathVariable Long instituteId){
        InstituteDTO createInstitute=this.instituteService.updateInstitute(instituteDTO,instituteId);
        return new ResponseEntity<> (createInstitute, HttpStatus.CREATED);
    }

    @GetMapping("/{instituteId}")
    public ResponseEntity<InstituteDTO>getInstitute(@PathVariable Long instituteId){
        InstituteDTO createInstitute=this.instituteService.getInstitute(instituteId);
        return new ResponseEntity<> (createInstitute, HttpStatus.CREATED);
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstitute(@PathVariable Long instituteId){
        String createInstitute=this.instituteService.deleteInstitute(instituteId);
        return new ResponseEntity<> ("", HttpStatus.GONE);
    }

    @GetMapping("/getAllInstitutes")
    public ResponseEntity<InstituteResponse> getInstitutes(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_INSTITUTES_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        InstituteResponse instituteResponse=this.instituteService.getInstitutes(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<> (instituteResponse, HttpStatus.CREATED);
    }

    @GetMapping("/byEmail/{emailId}/")
    public ResponseEntity<InstituteDTO> registerInstitute(@PathVariable String emailId){
        InstituteDTO createInstitute=this.instituteService.getInstituteByEmail(emailId);
        return new ResponseEntity<> (createInstitute, HttpStatus.CREATED);
    }

    @GetMapping("/downloadInstitutesCSV")
    public ResponseEntity<byte[]> downloadInstitutesCSV() throws IOException {
        List<InstituteDTO> institutes = instituteService.getAllInstitutesCsv(); // Assuming you have a method to get all institutes

        byte[] csvBytes = CsvUtils.writeInstitutesToCsv(institutes);

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=institutes.csv")
                .body(csvBytes);
    }

    @GetMapping("/downloadInstitutesExcel")
    public ResponseEntity<byte[]> downloadInstitutesExcel() throws IOException {
        List<InstituteDTO> institutes = instituteService.getAllInstitutesExcel (); // Assuming you have a method to get all institutes

        byte[] excelBytes = ExcelUtils.writeInstitutesToExcel(institutes);

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=institutes.xlsx")
                .body(excelBytes);
    }
}
