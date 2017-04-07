package com.kofianan.lms.dashboard.service.dto;

import com.kofianan.lms.dashboard.config.Constants;

import com.kofianan.lms.dashboard.domain.Authority;
import com.kofianan.lms.dashboard.domain.User;

import lombok.Data;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
@Data
public class UserDTO {

	private String id;

	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String login;

	private String password;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	@Size(max = 50)
	private String gender;

	@Size(max = 50)
	private String nationality;

	@Size(max = 50)
	private String faculty;

	private LocalDate dateOfBirth;

	private String address;

	@Size(max = 50)
	private String phoneNumber;

	@Size(max = 50)
	private String passportNumber;

	@Email
	@Size(min = 5, max = 100)
	private String email;

	@Size(max = 256)
	private String imageUrl;

	private boolean activated = false;

	@Size(min = 2, max = 5)
	private String langKey;

	private String createdBy;

	private ZonedDateTime createdDate;

	private String lastModifiedBy;

	private ZonedDateTime lastModifiedDate;

	private Set<String> authorities;

	public UserDTO() {
		// Empty constructor needed for MapStruct.
	}

	public UserDTO(User user) {
		this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(),
				user.getGender(), user.getNationality(), user.getFaculty(), user.getDateOfBirth(),
				user.getAddress(), user.getPhoneNumber(),user.getPassportNumber()	,		
				user.getEmail(), user.isActivated(), user.getImageUrl(), user.getLangKey(),
				user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
				user.getAuthorities().stream().map(Authority::getName)
				.collect(Collectors.toSet()));
	}

	public UserDTO(String id, String login, String firstName, String lastName,
			String gender, String nationality, String faculty, LocalDate dateOfBirth, 
			String address, String phoneNumber, String passportNumber,
			String email, boolean activated, String imageUrl, String langKey,
			String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate,
			Set<String> authorities) {

		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.faculty = faculty;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.passportNumber = passportNumber;		
		this.email = email;
		this.activated = activated;
		this.imageUrl = imageUrl;
		this.langKey = langKey;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.authorities = authorities;
	}


}
