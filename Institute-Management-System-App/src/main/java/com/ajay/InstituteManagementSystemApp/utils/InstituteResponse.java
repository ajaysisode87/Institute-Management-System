package com.ajay.InstituteManagementSystemApp.utils;

import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstituteResponse {

    private List<InstituteDTO> instituteDTOS;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private Boolean lastPage;
}
