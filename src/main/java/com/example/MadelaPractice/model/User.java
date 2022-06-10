package com.example.MadelaPractice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class User {
    private Long id;
    private Long officeId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private Long docCode;
    private String docNumber;
    private Date docDate;
    private Long citizenshipCode;
    private Boolean isIdentified;

}
