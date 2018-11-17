package com.spm.erp.model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
   
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Date dob;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "address_id")
    private Integer addressId;
    
    @Column(name = "email")
    private String email;
  
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "department_id")
    private Integer departmentId;
    
    @Column(name = "job_id")
    private Integer jobId;
    
    @Column(name = "password")
    private String password;

}