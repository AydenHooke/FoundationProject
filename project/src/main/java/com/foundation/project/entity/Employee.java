package com.foundation.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

enum authorityLevel{
    DEFAULT,
    FINANCEMANAGER
}

@Entity
@Table(name="employee")
public class Employee {

    @Column(name="employeeId")
    @Id
    @GeneratedValue
    @Getter
    @Setter

    private Integer employeeId;
    /*
     * This is an ID for the annount
     */
    private String username;
    /*
     * This is a username for the account - it must be unique and not blank
     */
    private String password;
    /*
     * This must not be blank, no other parameters set
     */
    private authorityLevel authority;
    /*
     * This is a measure of an account's authority
     * 0 = default employee (new-hire)
     * 1 = Finance Manager
     */
    public Employee(){

    }

    public Employee(String username, String password){
        this.username = username;
        this.password = password;
        this.authority = authorityLevel.DEFAULT;
    }

    public Employee(String username, String password, Integer authority){
        this.username = username;
        this.password = password;
        this.authority = authorityLevel.DEFAULT;
    }

    public Employee(Integer employeeId, String username, String password, Integer authority){
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.authority = authorityLevel.DEFAULT;
    }

}
