package com.ajay.InstituteManagementSystemApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
@Entity
@Table(name = "institutes")
@Data
@Builder
@AllArgsConstructor
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "institute_id")
    private Long id;
    @Size(min = 15,max = 100,message = "Institute name must be between 15 t0 100 character")
   // @Pattern (regexp = "^[a-zA-Z]*$]",message = "No Special Character in Institute name")
    private String name;
    @Size(max = 255, message = "Location should not exceed 255 characters")
    private String location;

    @Size(max = 20, message = "Contact information should not exceed 20 characters")
    private String contactInformation;

    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String emailId;

    @Size(max = 15, message = "Mobile number should not exceed 15 characters")
    private String mobileNumber;
    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    @LastModifiedDate
    private LocalDate updatedDate;

    public Institute(String name, String location, String contactInformation, String emailId, String mobileNumber) {
        this.name = name;
        this.location = location;
        this.contactInformation = contactInformation;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
    }
    public Institute(){}
}
