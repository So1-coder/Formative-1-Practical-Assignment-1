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