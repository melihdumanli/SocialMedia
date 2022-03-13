package com.bilgeadam.graphql.model;

import lombok.Data;

@Data
public class ProfileInput {
    String profileId;
    String firstname;
    String lastname;
    String email;
    String city;
    String country;
}
