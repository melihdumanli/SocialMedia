package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginResponseDto {
    String  profileid;
    String token;
    /** status will show that if user is active or not.
     * 0-> passive user
     * 1-> active user
     * 2-> banned user
     * */
    int status;
    /** 200 : successful
     *  400 : request failed
     *  410 : user not found
     *  411: token error
     *  500 : unexpected error
    * */
    int error;
}
