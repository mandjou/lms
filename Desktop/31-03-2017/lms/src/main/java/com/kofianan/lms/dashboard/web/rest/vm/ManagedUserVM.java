package com.kofianan.lms.dashboard.web.rest.vm;

import com.kofianan.lms.dashboard.service.dto.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(String id, String login, String password, String firstName, String lastName,
    		String gender, String nationality, String faculty, LocalDate dateOfBirth, String address, String phoneNumber, String passportNumber,
                         String email, boolean activated, String imageUrl, String langKey,
                         String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate,
                        Set<String> authorities) {

    	super(id, login, firstName, lastName, gender, nationality, faculty, dateOfBirth, address, phoneNumber, passportNumber, email, activated, imageUrl, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities);
//        super(id, login, firstName, lastName, email, activated, imageUrl, langKey,
//            createdBy, createdDate, lastModifiedBy, lastModifiedDate,  authorities);

        this.password = password;
    }

}
