package com.foundation.project;

import com.foundation.project.entity.Employee;
import com.foundation.project.repository.EmployeeRepository;
import com.foundation.project.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        // Setup a sample employee for testing
        testEmployee = new Employee("testUser", "testPassword", 0);
    }

    @Test
    void testCreateEmployee_Success() {
        // Mock the behavior of findEmployeeByUsername method
        when(employeeRepository.findEmployeeByUsername(testEmployee.getUsername())).thenReturn(null); // No user exists

        // Mock the save method to return the employee
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        // Call the createEmployee method
        Employee createdEmployee = employeeService.createEmployee(testEmployee);

        // Assertions
        assertNotNull(createdEmployee);
        assertEquals(testEmployee.getUsername(), createdEmployee.getUsername());
        assertEquals(0, createdEmployee.getAccessLevel()); // Default access level
    }

    @Test
    void testCreateEmployee_UsernameAlreadyExists() {
        // Mock the behavior of findEmployeeByUsername method to simulate an existing user
        when(employeeRepository.findEmployeeByUsername(testEmployee.getUsername())).thenReturn(testEmployee);

        // Call the createEmployee method
        Employee createdEmployee = employeeService.createEmployee(testEmployee);

        // Assertions
        assertNull(createdEmployee); // Should return null since the username already exists
    }

    @Test
    void testProcessLogin_Success() {
        // Mock the behavior of findEmployeeByUsernameAndPassword method
        when(employeeRepository.findEmployeeByUsernameAndPassword(testEmployee.getUsername(), testEmployee.getPassword()))
                .thenReturn(testEmployee);

        // Call the processLogin method
        Employee loggedInEmployee = employeeService.processLogin(testEmployee);

        // Assertions
        assertNotNull(loggedInEmployee);
        assertEquals(testEmployee.getUsername(), loggedInEmployee.getUsername());
    }

    @Test
    void testProcessLogin_Failure() {
        // Mock the behavior of findEmployeeByUsernameAndPassword method to return null
        when(employeeRepository.findEmployeeByUsernameAndPassword(testEmployee.getUsername(), testEmployee.getPassword()))
                .thenReturn(null);

        // Call the processLogin method
        Employee loggedInEmployee = employeeService.processLogin(testEmployee);

        // Assertions
        assertNull(loggedInEmployee); // Should return null as login fails
    }

    @Test
    void testPromoteMe_Success() {
        // Mock the behavior of findEmployeeByUsername method to return the employee
        when(employeeRepository.findEmployeeByUsername(testEmployee.getUsername())).thenReturn(testEmployee);

        // Mock the behavior of the save method
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

        // Call the promoteMe method
        Employee promotedEmployee = employeeService.promoteMe(testEmployee, 6969);

        // Assertions
        assertNotNull(promotedEmployee);
        assertEquals(1, promotedEmployee.getAccessLevel()); // Access level should increase by 1
    }

    @Test
    void testPromoteMe_Failure_InvalidPromotionCode() {
        // Mock the behavior of findEmployeeByUsername method to return the employee
        when(employeeRepository.findEmployeeByUsername(testEmployee.getUsername())).thenReturn(testEmployee);

        // Call the promoteMe method with an invalid promotion code
        Employee promotedEmployee = employeeService.promoteMe(testEmployee, 1234);

        // Assertions
        assertNull(promotedEmployee); // Should return null as the promotion code is invalid
    }

    @Test
    void testFindEmployeeById_Failure() {
        // Mock the behavior of findEmployeeByEmployeeId method to return null
        when(employeeRepository.findEmployeeByEmployeeId(1)).thenReturn(null);

        // Call the findEmployeeById method
        Employee foundEmployee = employeeService.findEmployeeById(1);

        // Assertions
        assertNull(foundEmployee); // Should return null as the employee is not found
    }
}