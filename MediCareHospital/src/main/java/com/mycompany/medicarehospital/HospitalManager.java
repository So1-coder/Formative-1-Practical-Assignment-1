/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author Solo
 */

import java.util.ArrayList;
import java.util.Comparator;

public class HospitalManager {

    private ArrayList<Patient> patients;

    private String[][] beds;

    public HospitalManager() {

        patients = new ArrayList<>();

        beds = new String[4][5];

        int bedNumber = 1;

        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                beds[row][col] = String.format("B%02d", bedNumber);
                bedNumber++;
            }
        }
    }

    public void registerPatient(Patient patient)
            throws HospitalException {

        if (patient == null) {
            throw new HospitalException("Patient cannot be null.");
        }

        if (findPatient(patient.getPatientId()) != null) {
            throw new HospitalException(
                    "Patient ID already exists."
            );
        }

        patients.add(patient);
    }

    public Patient findPatient(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId()
                    .equalsIgnoreCase(patientId)) {

                return patient;
            }
        }

        return null;
    }

    public void updatePatient(String patientId,
                              String firstName,
                              String lastName,
                              int age,
                              String gender,
                              String medicalCondition)
            throws HospitalException {

        Patient patient = findPatient(patientId);

        if (patient == null) {
            throw new HospitalException(
                    "Patient not found."
            );
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
    }


}


/***************************************************************************************
***************************************************************************************
*Title: ArrayList in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/arraylist-in-java/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
*Title: Java Constructors
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/constructors-in-java/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
*Title: Java Constructors
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/constructors-in-java/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
*Title: Getter and Setter in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/getter-and-setter-in-java/
***************************************************************************************
***************************************************************************************/