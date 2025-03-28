package com.management.patient.Service;

import com.management.patient.Dto.PatientRequestDTO;
import com.management.patient.Dto.PatientResponseDTO;
import com.management.patient.Entity.Patient;
import com.management.patient.Exception.EmailAlreadyExistsException;
import com.management.patient.Exception.PatientNotFoundException;
import com.management.patient.Mapper.PatientMapper;
import com.management.patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) throws Exception {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with id " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
//        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);

    }
}
