package com.management.patient.Controller;

import com.management.patient.Dto.PatientRequestDTO;
import com.management.patient.Dto.PatientResponseDTO;
import com.management.patient.Dto.Validators.CreatePatientValidationGroup;
import com.management.patient.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO) throws Exception {

        PatientResponseDTO patientResponseDTO = patientService.createPatient(
                patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,
                patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an patient")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Deleted Patient successfully");
    }
}
