package com.kofianan.lms.dashboard.domain;

import com.kofianan.lms.dashboard.config.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A user.
 */

@Data
@EqualsAndHashCode(callSuper=true)
@Document(collection = "lms_user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Indexed
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("last_name")
    private String lastName;
    
    @Size(max = 50)
    @Field("gender")
    private String gender;
    
    @Size(max = 50)
    @Field("nationality")
    private String nationality;
    
    @Size(max = 50)
    @Field("faculty")
    private String faculty;
    
    @Field("date_of_Birth")
    private LocalDate dateOfBirth;
    
    @Field("address")
    private String address;
    
    @Size(max = 50)
    @Field("phone_number")
    private String phoneNumber;

    @Size(max = 50)
    @Field("passport_number")
    private String passportNumber;
    
    @Email
    @Size(min = 5, max = 100)
    @Indexed
    private String email;
    

    private boolean activated = false;

    @Size(min = 2, max = 5)
    @Field("lang_key")
    private String langKey;

    @Size(max = 256)
    @Field("image_url")
    private String imageUrl;

    @Size(max = 20)
    @Field("activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Field("reset_key")
    private String resetKey;

    @Field("reset_date")
    private ZonedDateTime resetDate = null;

    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();

    
    //Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

 
}
