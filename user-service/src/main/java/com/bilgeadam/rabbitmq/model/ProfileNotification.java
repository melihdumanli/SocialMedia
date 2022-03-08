package com.bilgeadam.rabbitmq.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileNotification implements Serializable {
    String profileId;
    String firstname;
    String lastname;
    String email;
    String city;
    String country;
}
