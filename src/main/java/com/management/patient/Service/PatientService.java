package com.management.patient.Service;

import com.management.patient.Dto.PatientRequestDTO;
import com.management.patient.Dto.PatientResponseDTO;
import com.management.patient.Entity.Patient;
import com.management.patient.Mapper.PatientMapper;
import com.management.patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
