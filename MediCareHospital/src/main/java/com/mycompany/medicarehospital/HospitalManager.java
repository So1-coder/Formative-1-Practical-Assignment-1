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

    public void deletePatient(String patientId)
            throws HospitalException {

        Patient patient = findPatient(patientId);

        if (patient == null) {
            throw new HospitalException(
                    "Patient not found."
            );
        }

        if (patient instanceof Inpatient) {

            Inpatient inpatient = (Inpatient) patient;

            if (inpatient.getBedNumber() != null) {
                releaseBed(inpatient.getBedNumber());
            }
        }

        patients.remove(patient);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }


    public String[][] getBeds() {
        return beds;
    }

    public boolean isBedAvailable(String bedNumber) {

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0; col < beds[row].length; col++) {

                if (beds[row][col].equalsIgnoreCase(bedNumber)) {
                    return true;
                }
            }
        }

        return false;
    }

    public String findPatientBed(String patientId) {

        Patient patient = findPatient(patientId);

        if (patient instanceof Inpatient) {

            Inpatient inpatient = (Inpatient) patient;

            return inpatient.getBedNumber();
        }

        return null;
    }

    public void allocateBed(String patientId, String bedNumber)
            throws HospitalException {

        Patient patient = findPatient(patientId);

        if (patient == null) {
            throw new HospitalException(
                    "Patient not found."
            );
        }

        if (patient.getCategory() != PatientCategory.INPATIENT) {
            throw new HospitalException(
                    "Only Inpatients may be allocated a bed."
            );
        }

        Inpatient inpatient = (Inpatient) patient;

        if (inpatient.getBedNumber() != null
                && !inpatient.getBedNumber().isEmpty()) {

            throw new HospitalException(
                    "Patient already has a bed."
            );
        }

        int[] position = findBedPosition(bedNumber);

        if (position == null) {
            throw new HospitalException(
                    "Invalid bed number."
            );
        }

        int row = position[0];
        int col = position[1];

        if (!isBedOccupied(bedNumber)) {

            beds[row][col] =
                    bedNumber.toUpperCase()
                    + " - " + patientId;

            inpatient.setBedNumber(
                    bedNumber.toUpperCase()
            );

            inpatient.setWardNumber("Ward 1");

        } else {

            throw new HospitalException(
                    "Bed is already occupied."
            );
        }
    }

    public void releaseBed(String bedNumber)
            throws HospitalException {

        int[] position = findBedPosition(bedNumber);

        if (position == null) {
            throw new HospitalException(
                    "Invalid bed number."
            );
        }

        int row = position[0];
        int col = position[1];

        if (!isBedOccupied(bedNumber)) {
            throw new HospitalException(
                    "Bed is already available."
            );
        }

        String originalBed =
                String.format(
                        "B%02d",
                        (row * 5) + col + 1
                );

        String value = beds[row][col];

        if (value.contains(" - ")) {

            String patientId =
                    value.substring(
                            value.indexOf(" - ") + 3
                    );

            Patient patient = findPatient(patientId);

            if (patient instanceof Inpatient) {

                Inpatient inpatient =
                        (Inpatient) patient;

                inpatient.setBedNumber(null);
                inpatient.setWardNumber(null);
            }
        }

        beds[row][col] = originalBed;
    }

    private int[] findBedPosition(String bedNumber) {

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0;
                    col < beds[row].length;
                    col++) {

                String current =
                        beds[row][col];

                if (current.equalsIgnoreCase(bedNumber)
                        || current.startsWith(
                                bedNumber.toUpperCase()
                                + " - ")) {

                    return new int[]{row, col};
                }
            }
        }

        return null;
    }

    public boolean isBedOccupied(String bedNumber) {

        int[] position =
                findBedPosition(bedNumber);

        if (position == null) {
            return false;
        }

        String value =
                beds[position[0]][position[1]];

        return value.contains(" - ");
    }

    public boolean areAllBedsOccupied() {

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0;
                    col < beds[row].length;
                    col++) {

                if (!beds[row][col].contains(" - ")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void displayWardLayout() {

        System.out.println();
        System.out.println("========== WARD LAYOUT ==========");

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0;
                    col < beds[row].length;
                    col++) {

                System.out.printf(
                        "%-18s",
                        beds[row][col]
                );
            }

            System.out.println();
        }

        System.out.println("==================================");
    }

    public void displayAvailableBeds() {

        System.out.println();
        System.out.println("========== AVAILABLE BEDS ==========");

        boolean found = false;

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0;
                    col < beds[row].length;
                    col++) {

                if (!beds[row][col].contains(" - ")) {

                    System.out.print(
                            beds[row][col] + " "
                    );

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No beds available.");
        }

        System.out.println();
        System.out.println("====================================");
    }

    public void displayOccupiedBeds() {

        System.out.println();
        System.out.println("========== OCCUPIED BEDS ==========");

        boolean found = false;

        for (int row = 0; row < beds.length; row++) {

            for (int col = 0;
                    col < beds[row].length;
                    col++) {

                if (beds[row][col].contains(" - ")) {

                    System.out.print(
                            beds[row][col] + " "
                    );

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No beds occupied.");
        }

        System.out.println();
        System.out.println("====================================");
    }

    public void displayAllPatients() {

        System.out.println();
        System.out.println("========== PATIENT REPORT ==========");

        if (patients.isEmpty()) {

            System.out.println("No patients registered.");

        } else {

            for (Patient patient : patients) {

                patient.displayDetails();
            }
        }

        System.out.println("====================================");
    }

    public int getTotalPatients() {
        return patients.size();
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
/***************************************************************************************
***************************************************************************************
*Title: System.out.println in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/system-out-println-in-java/
***************************************************************************************
***************************************************************************************/