/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author Solo
 */

public class Patient {

    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    public Patient(String patientId, String firstName, String lastName,
                   int age, String gender, String medicalCondition,
                   PatientCategory category) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }
}



/***************************************************************************************
***************************************************************************************
*Title: Java Constructors
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/java/constructors-in-java/
***************************************************************************************
***************************************************************************************/