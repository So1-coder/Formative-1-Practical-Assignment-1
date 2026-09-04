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