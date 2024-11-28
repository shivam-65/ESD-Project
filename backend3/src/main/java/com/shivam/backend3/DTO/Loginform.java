package com.shivam.backend3.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record Loginform(
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
) {
}
