package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tbluser")
@Entity
public class User {

    @SequenceGenerator(name = "sq_tbluser_id", sequenceName = "sq_tbluser_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "sq_tbluser_id")
    @Id
    long id;
    String username;
    String password;
    /*
    * Status describes whether the user is active or not.
    *
    * 0-> passive user
    * 1-> active user
    * 2-> banned user
    */
    int status;
    long createDate;
    long updateDate;
}
