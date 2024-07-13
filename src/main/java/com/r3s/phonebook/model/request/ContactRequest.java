package com.r3s.phonebook.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactRequest {
    @Schema(description = "ID of the contact", required = true, example = "0")
    private Long id;

    @Schema(description = "First name of the contact", required = false, example = "Yanto")
    @NotBlank
    @NotNull
    private String firstName;
    @Schema(description = "Last name of the contact", required = false, example = "Kopling")
    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Schema(description = "Phone number of the contact", required = false, example = "087xxxxxxxxx")
    private String phoneNumber;

    @Schema(description = "Email of the contact", required = false, example = "yanto@example.com")
    @Email
    private String email;

    @Schema(description = "Note about the contact", required = false, example = "Yanto Tukang Bakso")
    private String note;

    @Schema(description = "Group of the contact", required = false, example = "Tetangga")
    private String group;
}
