package com.bb.recruitment.datamodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDetails {
    @JsonView(UserDetailsView.RegisterNewUser.class)
    @JsonProperty("email")
    public String email;
    @JsonView(UserDetailsView.AllFields.class)
    @JsonProperty("token")
    public String token;
    @JsonView(UserDetailsView.RegisterNewUser.class)
    @JsonProperty("username")
    public String username;
    @JsonView(UserDetailsView.RegisterNewUser.class)
    @JsonProperty("password")
    public String password;
    @JsonView(UserDetailsView.SettingsFields.class)
    @JsonProperty("bio")
    public String bio;
    @JsonView(UserDetailsView.SettingsFields.class)
    @JsonProperty("image")
    public String image;

    public UserDetails(String username, String email, String pass) {
        this.username = username;
        this.email = email;
        this.password = pass;
    }
}
