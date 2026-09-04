/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author Solo
 */

public class Inpatient extends Patient {

    private String wardNumber;
    private String bedNumber;

    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition,
                     String wardNumber, String bedNumber) {

        super(patientId, firstName, lastName, age, gender,
              medicalCondition, PatientCategory.INPATIENT);

        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Bed Number: " + bedNumber);
    }
}

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
*Title: Super Keyword in Java
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/super-keyword/
***************************************************************************************
***************************************************************************************/