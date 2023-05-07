package com.jolly.userservice.model.rest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author jolly
 */
@Getter
@Setter
public class UserResponse {
   private String firstName;
   private String lastName;
   private List<AccountResponse> bankAccounts;
   private String identificationNumber;
   private Integer id;
   private String email;
}
