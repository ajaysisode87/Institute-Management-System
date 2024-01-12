package com.ajay.InstituteManagementSystemApp.serviceImpl;

import com.ajay.InstituteManagementSystemApp.entities.Institute;
import com.ajay.InstituteManagementSystemApp.exception.ResourceNotFoundException;
import com.ajay.InstituteManagementSystemApp.payloads.InstituteDTO;
import com.ajay.InstituteManagementSystemApp.repositories.InstituteRepository;
import com.ajay.InstituteManagementSystemApp.service.InstituteService;
import com.ajay.InstituteManagementSystemApp.utils.InstituteResponse;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class InstituteServiceImpl implements InstituteService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InstituteRepository instituteRepository;

    public InstituteServiceImpl(ModelMapper modelMapper, InstituteRepository instituteRepository) {
        this.modelMapper = modelMapper;
        this.instituteRepository = instituteRepository;
    }

    @Override
    public InstituteDTO registerInstitute(InstituteDTO instituteDTO) {
        try {
            Optional<Institute> instituteFromDB = this.instituteRepository.findByName(instituteDTO.getName());
            log.info("instituteDTO reached Here {}", instituteDTO);

            if (instituteFromDB.isPresent()) {
                throw new DataIntegrityViolationException("Institute is already present with this Name");
            }

            Institute saveInstituteToDB = this.dtoToInstitute(instituteDTO);
            this.instituteRepository.save(saveInstituteToDB);

            return this.instituteToDTO(saveInstituteToDB);
        } catch (DataIntegrityViolationException e) {
            System.out.println (e.getMessage ());
            throw new RuntimeException("Error while registering institute", e);
        }
    }


    @Override
    public InstituteDTO updateInstitute(InstituteDTO instituteDTO, Long instituteId) {

       Institute instituteFromDB= this.instituteRepository
        .findById (instituteId)
                .orElseThrow (()->new ResourceNotFoundException ("Institute","Institute",instituteId));
       if (instituteId==instituteFromDB.getId ()){
           instituteFromDB.setName (instituteDTO.getName ());
           instituteFromDB.setLocation (instituteDTO.getLocation ());
           instituteFromDB.setContactInformation (instituteDTO.getContactInformation ());
           instituteFromDB.setEmailId (instituteDTO.getEmailId ());
           instituteFromDB.setMobileNumber (instituteDTO.getMobileNumber ());

          Institute instituteSaveToDB= this.instituteRepository.save (instituteFromDB);
          return this.instituteToDTO (instituteSaveToDB);
       }
       throw new RuntimeException ("Internal Server Error");
    }

    @Override
    public String deleteInstitute(Long instituteId) {
        Institute instituteFromDB =this.instituteRepository
                .findById (instituteId)
                .orElseThrow (()->new ResourceNotFoundException ("Institute","Institute",instituteId));
        if (instituteId!=null){
            this.instituteRepository.delete (instituteFromDB);
        }
        return "Institute successfully deleted with INPUT_ID :"+instituteId;
    }

    @Override
    public InstituteDTO getInstitute(Long instituteId) {
        Institute instituteFromDB =this.instituteRepository
                .findById (instituteId)
                .orElseThrow (()->new ResourceNotFoundException ("Institute","Institute",instituteId));
        return this.instituteToDTO (instituteFromDB);
    }

    @Override
    public InstituteResponse getInstitutes(Integer pageNumber,Integer pageSize,String sortBy,String  sortOrder) {
        Sort sortByAndOrder =sortOrder
                .equalsIgnoreCase ("asc")
                ?Sort.by (sortBy).ascending ()
                :Sort.by (sortBy).descending ();

// Use by Pageable object
        Pageable pageDetails =PageRequest.of (pageNumber,pageSize,sortByAndOrder);

// Return page records
        Page<Institute> institutePage =this.instituteRepository.findAll (pageDetails);

//getInstitutes from institutePage
        List<Institute> listOfInstitute=institutePage.getContent ();
//
        List<InstituteDTO> instituteDTOList=listOfInstitute
                .stream ()
                .map (institute -> {
                    InstituteDTO instituteDTO=instituteToDTO (institute);
                   return instituteDTO;
                }).collect (Collectors.toList ());
        /*Create Institute Response Object
        * */
      InstituteResponse instituteResponse=new InstituteResponse ();
      instituteResponse.setInstituteDTOS (instituteDTOList);
      instituteResponse.setPageNumber (institutePage.getNumber ());
      instituteResponse.setPageSize (institutePage.getSize ());
      instituteResponse.setLastPage (institutePage.isLast ());
      instituteResponse.setTotalPages (institutePage.getTotalPages ());
      instituteResponse.setTotalElements (institutePage.getTotalElements ());

        return instituteResponse;
    }

    @Override
    public InstituteDTO getInstituteByEmail(String emailId) {
        Institute instituteFromDB =this.instituteRepository.findByEmailId(emailId);
        return this.instituteToDTO (instituteFromDB);
    }

    public List<InstituteDTO> getAllInstitutesExcel(){
        List<Institute> listOfInstitute=this.instituteRepository.findAll ();
        return listOfInstitute
                .stream ()
                .map (institute -> instituteToDTO (institute))
                .collect(Collectors.toList());
    }

    public List<InstituteDTO> getAllInstitutesCsv(){
        List<Institute> listOfInstitute=this.instituteRepository.findAll ();
        return listOfInstitute
                .stream ()
                .map (institute -> instituteToDTO (institute))
                .collect(Collectors.toList());
    }

    //below method convert Institute to InstituteDTO
    private InstituteDTO instituteToDTO(Institute institute){
        return
                this.modelMapper
                        .map (institute,InstituteDTO.class);
    }
    //below method convert InstituteDTO to Institute
    private Institute dtoToInstitute(InstituteDTO instituteDTO){
        return
                this.modelMapper
                        .map (instituteDTO,Institute.class);
    }
}
