package com.hospital.hospitalmanagementsystem;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class PatientController {
    HashMap<Integer,Patient> patientDb=new HashMap<>();
    @PostMapping("/addPatientViaParameters")
    public String addPatient(@RequestParam("patientId") Integer patientId, @RequestParam("name") String name, @RequestParam("age") Integer age,@RequestParam("disease") String disease){
        Patient pt=new Patient(patientId,name,disease,age);
        patientDb.put(patientId,pt);
        return "Patient added successfully";
    }
    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){
        int key=patient.getId();
        patientDb.put(key,patient);
        return "Patient added successfully";
    }
    @GetMapping("/patientInfoViaPathVariable/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId") Integer patientId){
        return patientDb.get(patientId);
    }
    @GetMapping("/patientInfo")
    public Patient getPatient(@RequestParam("patientId") Integer patientId){
        return patientDb.get(patientId);
    }
    @GetMapping("/getAllPatient")
    public List<Patient> getAllPatient(){
        return new ArrayList<>(patientDb.values());
    }
//    @GetMapping("getPatientByName")
//    public Patient getPatient(@RequestParam("patientName")String patientName){
//        return patientDb.get()
//    }
    @GetMapping("/getPatientListGreaterThanAge")
    public List<Patient> getPatientListGreaterThanAge(@RequestParam("age") Integer age){
        List<Patient> list=new ArrayList<>();
        for(Patient p:patientDb.values()){
            if(p.getAge()>age){
                list.add(p);
            }
        }
        return list;
    }
    @GetMapping("/getPatientInfoGreaterThanAgeAndParticularDisease/{age}/{disease}")
    public List<Patient> getPatientInfoGreaterThanAgeAndParticularDisease(@PathVariable("age") int age,@PathVariable("age") String disease){
        List<Patient> list=new ArrayList<>();
        for(Patient p:patientDb.values()){
            if(p.getAge()>age && p.getDisease().equals(disease)){
                list.add(p);
            }
        }
        return list;
    }
    @PutMapping("/updatePatientInfo")
    public String updatePatientInfo(@RequestBody Patient p){
        if(patientDb.containsKey(p.getId())){
            patientDb.put(p.getId(),p);
            return "Updated Succesfully";
        }

        return "Data does not exist";
    }


}
