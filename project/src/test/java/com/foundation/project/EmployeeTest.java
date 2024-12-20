package com.foundation.project;

import org.junit.jupiter.api.Test;

import com.foundation.project.entity.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeConstructor_NoArgs() {
        // Create employee using no-argument constructor
        Employee employee = new Employee();

        // Verify the initial state of the employee
        assertNull(employee.getUsername());
        assertNull(employee.getPassword());
        assertNull(employee.getAccessLevel());
        assertNull(employee.getEmployeeId());
    }

    @Test
    void testEmployeeConstructor_UsernamePassword() {
        // Create employee with username and password
        Employee employee = new Employee("jdoe", "password123");

        // Verify the state of the employee
        assertEquals("jdoe", employee.getUsername());
        assertEquals("password123", employee.getPassword());
        assertNull(employee.getAccessLevel());
        assertNull(employee.getEmployeeId());
    }

    @Test
    void testEmployeeConstructor_UsernamePasswordAccessLevel() {
        // Create employee with username, password, and access level
        Employee employee = new Employee("jdoe", "password123", 1);

        // Verify the state of the employee
        assertEquals("jdoe", employee.getUsername());
        assertEquals("password123", employee.getPassword());
        assertEquals(1, employee.getAccessLevel());
        assertNull(employee.getEmployeeId());
    }

    @Test
    void testEmployeeConstructor_AllArgs() {
        // Create employee with all arguments
        Employee employee = new Employee(1, "jdoe", "password123", 1);

        // Verify the state of the employee
        assertEquals(1, employee.getEmployeeId());
        assertEquals("jdoe", employee.getUsername());
        assertEquals("password123", employee.getPassword());
        assertEquals(1, employee.getAccessLevel());
    }

    @Test
    void testAccessLevel_ValidValues() {
        // Create employees with valid access levels
        Employee defaultEmployee = new Employee("jdoe", "password123", 0);
        Employee financeManager = new Employee("finmgr", "securepass", 1);

        // Verify the access level values
        assertEquals(0, defaultEmployee.getAccessLevel());
        assertEquals(1, financeManager.getAccessLevel());
    }

    @Test
    void testAccessLevel_InvalidValue() {
        // Create employee with an invalid access level (example: 2)
        Employee employee = new Employee("jdoe", "password123", 2);

        // Since the class doesn't enforce any validation, we only check if the access level is set as provided
        assertEquals(2, employee.getAccessLevel());
    }

    @Test
    void testToString() {
        // Create an employee
        Employee employee = new Employee(1, "jdoe", "password123", 1);

        // Check if the toString method is working as expected
        String expectedString = "Employee(employeeId=1, username=jdoe, password=password123, accessLevel=1)";
        assertEquals(expectedString, employee.toString());
    }

    @Test
    void testEmployeeWithNullUsername() {
        // Create employee with a null username
        Employee employee = new Employee(null, "password123");

        // Verify the state of the employee
        assertNull(employee.getUsername());
        assertEquals("password123", employee.getPassword());
    }

    @Test
    void testEmployeeWithEmptyPassword() {
        // Create employee with an empty password
        Employee employee = new Employee("jdoe", "");

        // Verify the state of the employee
        assertEquals("jdoe", employee.getUsername());
        assertEquals("", employee.getPassword());
    }

    @Test
    void testEmployeeWithNullAccessLevel() {
        // Create employee with a null access level
        Employee employee = new Employee("jdoe", "password123", null);

        // Verify the state of the employee
        assertEquals("jdoe", employee.getUsername());
        assertEquals("password123", employee.getPassword());
        assertNull(employee.getAccessLevel());
    }

    @Test
    void testEmployeeWithAllNulls() {
        // Create employee with all null values
        Employee employee = new Employee(null, null, null);

        // Verify the state of the employee
        assertNull(employee.getUsername());
        assertNull(employee.getPassword());
        assertNull(employee.getAccessLevel());
    }
}
