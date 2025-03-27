package com.management.patient.Mapper;

import com.management.patient.Dto.PatientRequestDTO;
import com.management.patient.Dto.PatientResponseDTO;
import com.management.patient.Entity.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public static PatientRequestDTO toModel(Patient patient){
        PatientRequestDTO patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setName(patient.getName());
        patientRequestDTO.setEmail(patient.getEmail());
        patientRequestDTO.setAddress(patient.getAddress());
        patientRequestDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientRequestDTO.setRegisteredDate(patient.getRegisteredDate().toString());
        return patientRequestDTO;
    }
}
