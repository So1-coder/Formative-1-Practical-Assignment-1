/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author Solo
 */

import java.util.Scanner;

public class HospitalApp {

    private static Scanner scanner =
            new Scanner(System.in);

    private static HospitalManager manager =
            new HospitalManager();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt(
                    "Enter your choice: "
            );

            try {

                switch (choice) {

                    case 1:
                        registerPatient();
                        break;

                    case 2:
                        searchPatient();
                        break;

                    case 3:
                        updatePatient();
                        break;

                    case 4:
                        deletePatient();
                        break;

                    case 5:
                        manager.displayAllPatients();
                        break;

                    case 6:
                        allocateBed();
                        break;

                    case 7:
                        releaseBed();
                        break;

                    case 8:
                        manager.displayWardLayout();
                        break;

                    case 9:
                        manager.displayAvailableBeds();
                        break;

                    case 10:
                        manager.displayOccupiedBeds();
                        break;

                    case 11:
                        manager.displayWardReport();
                        break;

                    case 12:
                        sortPatients();
                        break;

                    case 0:
                        running = false;
                        System.out.println(
                                "Thank you for using "
                                + "MediCare Hospital System."
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice."
                        );
                }

            } catch (HospitalException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println(
                "=========================================="
        );
        System.out.println(
                "       MEDICARE HOSPITAL SYSTEM"
        );
        System.out.println(
                "=========================================="
        );

        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");
        System.out.println("6. Allocate Bed");
        System.out.println("7. Release Bed");
        System.out.println("8. Display Ward Layout");
        System.out.println("9. Display Available Beds");
        System.out.println("10. Display Occupied Beds");
        System.out.println("11. Ward Report");
        System.out.println("12. Sort Patients");
        System.out.println("0. Exit");

        System.out.println(
                "=========================================="
        );
    }

    private static void registerPatient()
            throws HospitalException {

        System.out.println();
        System.out.println(
                "========== REGISTER PATIENT =========="
        );

        String id = readString(
                "Patient ID: "
        );

        String firstName = readString(
                "First Name: "
        );

        String lastName = readString(
                "Last Name: "
        );

        int age = readInt(
                "Age: "
        );

        String gender = readString(
                "Gender: "
        );

        String condition = readString(
                "Medical Condition: "
        );

        System.out.println();
        System.out.println("Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        int categoryChoice = readInt(
                "Select category: "
        );

        Patient patient;

        switch (categoryChoice) {

            case 1:

                patient = new Inpatient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        "Ward 1",
                        null
                );

                break;

            case 2:

                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.OUTPATIENT
                );

                break;

            case 3:

                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.EMERGENCY
                );

                break;

            default:

                System.out.println(
                        "Invalid category."
                );

                return;
        }

        manager.registerPatient(patient);

        System.out.println(
                "Patient registered successfully!"
        );
    }

    private static void searchPatient() {

        String id = readString(
                "Enter Patient ID: "
        );

        Patient patient =
                manager.findPatient(id);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

        } else {

            patient.displayDetails();
        }
    }

    private static void updatePatient()
            throws HospitalException {

        String id = readString(
                "Enter Patient ID to update: "
        );

        Patient patient =
                manager.findPatient(id);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println(
                "Leave text blank to keep existing value."
        );

        String firstName = readString(
                "First Name [" +
                patient.getFirstName() + "]: "
        );

        String lastName = readString(
                "Last Name [" +
                patient.getLastName() + "]: "
        );

        String ageInput = readString(
                "Age [" +
                patient.getAge() + "]: "
        );

        String gender = readString(
                "Gender [" +
                patient.getGender() + "]: "
        );

        String condition = readString(
                "Medical Condition [" +
                patient.getMedicalCondition() + "]: "
        );

        if (firstName.isEmpty()) {
            firstName = patient.getFirstName();
        }

        if (lastName.isEmpty()) {
            lastName = patient.getLastName();
        }

        int age = patient.getAge();

        if (!ageInput.isEmpty()) {
            age = Integer.parseInt(ageInput);
        }

        if (gender.isEmpty()) {
            gender = patient.getGender();
        }

        if (condition.isEmpty()) {
            condition =
                    patient.getMedicalCondition();
        }

        manager.updatePatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition
        );

        System.out.println(
                "Patient updated successfully!"
        );
    }

    private static void deletePatient()
            throws HospitalException {

        String id = readString(
                "Enter Patient ID to delete: "
        );

        manager.deletePatient(id);

        System.out.println(
                "Patient deleted successfully!"
        );
    }

    private static void allocateBed()
            throws HospitalException {

        String id = readString(
                "Enter Inpatient ID: "
        );

        String bed = readString(
                "Enter bed number (e.g. B01): "
        );

        manager.allocateBed(id, bed);

        System.out.println(
                "Bed allocated successfully!"
        );
    }

    private static void releaseBed()
            throws HospitalException {

        String bed = readString(
                "Enter bed number: "
        );

        manager.releaseBed(bed);

        System.out.println(
                "Bed released successfully!"
        );
    }

    private static void sortPatients() {

        System.out.println();
        System.out.println(
                "========== SORT PATIENTS =========="
        );

        System.out.println("1. Sort by surname");
        System.out.println("2. Sort by Patient ID");

        int choice = readInt(
                "Enter choice: "
        );

        if (choice == 1) {

            manager.sortBySurname();

            System.out.println(
                    "Patients sorted by surname."
            );

            manager.displayAllPatients();

        } else if (choice == 2) {

            manager.sortByPatientId();

            System.out.println(
                    "Patients sorted by Patient ID."
            );

            manager.displayAllPatients();

        } else {

            System.out.println(
                    "Invalid choice."
            );
        }
    }

    private static String readString(
            String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}

/***************************************************************************************
***************************************************************************************
*Title: System.out.println in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/system-out-println-in-java/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
*Title: StringBuffer vs StringBuilder in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/stringbuffer-vs-stringbuilder/
/***************************************************************************************
/***************************************************************************************
*Title: Java String Manipulation: Best Practices For Clean Code*Author:GeeksForGeeks
*Date:2025
*Code version:
*Availability:https://www.geeksforgeeks.org/java/java-string-manipulation-best-practices-for-clean-code/
*
***************************************************************************************
***************************************************************************************
*/
/***************************************************************************************
***************************************************************************************
*Title: If statement in Programming
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/dsa/if-statement-in-programming/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
*Title: sort() in C++ STL
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/cpp/sort-c-stl/
***************************************************************************************
***************************************************************************************/