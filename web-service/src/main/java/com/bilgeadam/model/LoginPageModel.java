package com.bilgeadam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginPageModel {
    String title;
    List<product> products;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class product{
        String productName;
        String price;
    }
}
