package com.ajay.InstituteManagementSystemApp.service;

import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import com.ajay.InstituteManagementSystemApp.utils.InstituteResponse;

import java.util.List;

public interface InstituteService {
    InstituteDTO registerInstitute(InstituteDTO instituteDTO);

    InstituteDTO updateInstitute(InstituteDTO instituteDTO,Long instituteId);

    String deleteInstitute(Long instituteId);

    InstituteDTO getInstitute(Long instituteId);

    InstituteResponse getInstitutes(Integer pageNumber,Integer pageSize,String sortBy,String  sortOrder);

    InstituteDTO getInstituteByEmail(String emailId);

    List<InstituteDTO> getAllInstitutesExcel();
    List<InstituteDTO> getAllInstitutesCsv();
}
