/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author Solo
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalManagerTest {

    @Test
    public void testRegisterPatient() throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        assertNotNull(
                manager.findPatient("P001")
        );
    }

    @Test
    public void testSearchPatient()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Patient patient = new Patient(
                "P002",
                "Mary",
                "Jones",
                25,
                "Female",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        Patient found =
                manager.findPatient("P002");

        assertNotNull(found);

        assertEquals(
                "Mary",
                found.getFirstName()
        );
    }

    @Test
    public void testUpdatePatient()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Patient patient = new Patient(
                "P003",
                "Peter",
                "Brown",
                40,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        manager.updatePatient(
                "P003",
                "Peter",
                "Brown",
                41,
                "Male",
                "Pneumonia"
        );

        Patient updated =
                manager.findPatient("P003");

        assertEquals(
                41,
                updated.getAge()
        );

        assertEquals(
                "Pneumonia",
                updated.getMedicalCondition()
        );
    }

    @Test
    public void testDeletePatient()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Patient patient = new Patient(
                "P004",
                "James",
                "White",
                50,
                "Male",
                "Diabetes",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(patient);

        manager.deletePatient("P004");

        assertNull(
                manager.findPatient("P004")
        );
    }

    @Test
    public void testAllocateBed()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Inpatient patient =
                new Inpatient(
                        "P005",
                        "David",
                        "Green",
                        60,
                        "Male",
                        "Heart condition",
                        "Ward 1",
                        null
                );

        manager.registerPatient(patient);

        manager.allocateBed(
                "P005",
                "B01"
        );

        assertTrue(
                manager.isBedOccupied("B01")
        );

        assertEquals(
                "B01",
                patient.getBedNumber()
        );
    }

    @Test
    public void testReleaseBed()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Inpatient patient =
                new Inpatient(
                        "P006",
                        "Sarah",
                        "Black",
                        35,
                        "Female",
                        "Infection",
                        "Ward 1",
                        null
                );

        manager.registerPatient(patient);

        manager.allocateBed(
                "P006",
                "B02"
        );

        manager.releaseBed("B02");

        assertFalse(
                manager.isBedOccupied("B02")
        );

        assertNull(
                patient.getBedNumber()
        );
    }

    @Test
    public void testDuplicatePatientId() {

        HospitalManager manager =
                new HospitalManager();

        Patient patient1 = new Patient(
                "P007",
                "Tom",
                "Adams",
                20,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P007",
                "Jerry",
                "Adams",
                22,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        assertDoesNotThrow(() -> {
            manager.registerPatient(patient1);
        });

        HospitalException assertThrows = assertThrows(
                HospitalException.class,
                () -> manager.registerPatient(patient2)
        );
    }

    @Test
    public void testPreventOccupiedBed()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Inpatient patient1 =
                new Inpatient(
                        "P008",
                        "A",
                        "One",
                        30,
                        "Male",
                        "Flu",
                        "Ward 1",
                        null
                );

        Inpatient patient2 =
                new Inpatient(
                        "P009",
                        "B",
                        "Two",
                        35,
                        "Female",
                        "Cold",
                        "Ward 1",
                        null
                );

        manager.registerPatient(patient1);
        manager.registerPatient(patient2);

        manager.allocateBed(
                "P008",
                "B03"
        );

        HospitalException assertThrows = assertThrows(
                HospitalException.class,
                () -> manager.allocateBed(
                        "P009",
                        "B03"
                )
        );
    }

    @Test
    public void testPreventAllocationWhenFull()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        String[] bedNumbers = {
            "B01", "B02", "B03", "B04", "B05",
            "B06", "B07", "B08", "B09", "B10",
            "B11", "B12", "B13", "B14", "B15",
            "B16", "B17", "B18", "B19", "B20"
        };

        for (int i = 0; i < 20; i++) {

            String id =
                    String.format("P%03d", i + 1);

            Inpatient patient =
                    new Inpatient(
                            id,
                            "First",
                            "Patient" + i,
                            30,
                            "Male",
                            "Condition",
                            "Ward 1",
                            null
                    );

            manager.registerPatient(patient);

            manager.allocateBed(
                    id,
                    bedNumbers[i]
            );
        }

        assertTrue(
                manager.areAllBedsOccupied()
        );

        Inpatient extraPatient =
                new Inpatient(
                        "P021",
                        "Extra",
                        "Patient",
                        30,
                        "Male",
                        "Condition",
                        "Ward 1",
                        null
                );

        manager.registerPatient(extraPatient);

        HospitalException assertThrows = assertThrows(
                HospitalException.class,
                () -> manager.allocateBed(
                        "P021",
                        "B01"
                )
        );
    }

    @Test
    public void testSortPatients()
            throws HospitalException {

        HospitalManager manager =
                new HospitalManager();

        Patient p1 = new Patient(
                "P003",
                "John",
                "Zulu",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient p2 = new Patient(
                "P001",
                "Mary",
                "Adams",
                25,
                "Female",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        Patient p3 = new Patient(
                "P002",
                "Peter",
                "Brown",
                40,
                "Male",
                "Asthma",
                PatientCategory.OUTPATIENT
        );

        manager.registerPatient(p1);
        manager.registerPatient(p2);
        manager.registerPatient(p3);

        manager.sortBySurname();

        assertEquals(
                "Adams",
                manager.getPatients()
                        .get(0)
                        .getLastName()
        );

        assertEquals(
                "Brown",
                manager.getPatients()
                        .get(1)
                        .getLastName()
        );

        assertEquals(
                "Zulu",
                manager.getPatients()
                        .get(2)
                        .getLastName()
        );
    }
}

/***************************************************************************************
***************************************************************************************
*Title: Introduction to JUnit
*Author:GeeksForGeeks
*Date:2024
*Code version:
*Availability:https://www.geeksforgeeks.org/advance-java/introduction-of-junit/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
Title: assertEquals() vs. assertSame() in JUnit
*Author:GeeksForGeeks
*Date:2026
*Code version:
*Availability:https://www.geeksforgeeks.org/advance-java/assertequals-vs-assertsame-in-junit/
***************************************************************************************
***************************************************************************************/
/***************************************************************************************
***************************************************************************************
Title: Different Ways of Array Sorting Techniques in Java with JUnit
*Author:GeeksForGeeks
*Date:2025
*Code version:
*Availability:https://www.geeksforgeeks.org/software-testing/different-ways-of-array-sorting-techniques-in-java-with-junit/
***************************************************************************************
***************************************************************************************/