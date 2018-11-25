package com.spm.erp.model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(max = 40)
    @Email
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

    @Column(name = "manager_id")
    private Integer managerId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}