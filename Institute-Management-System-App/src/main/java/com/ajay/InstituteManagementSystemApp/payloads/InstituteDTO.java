package com.ajay.InstituteManagementSystemApp.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituteDTO {
    private Long id;
    private String name;
    private String location;
    private String contactInformation;
    private String emailId;
    private String mobileNumber;
}
