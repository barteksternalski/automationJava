package com.bb.recruitment.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Errors {
    @JsonProperty("errors")
    public ErrorDetails errors;
}
