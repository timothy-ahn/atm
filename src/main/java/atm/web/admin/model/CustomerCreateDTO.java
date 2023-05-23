package atm.web.admin.model;

import java.time.LocalDate;

import atm.entity.Customer.DocumentType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @NotBlank
    private String citizenship;
    @NotBlank
    @Size(min = 3, max = 3)
    private String stateIsoCode;
    @NotBlank
    private String sex;
    @Past
    private LocalDate birthDate;
    @NotBlank
    private String residenceAddress;
    @NotBlank
    private String actualAddress;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
    @NotNull
    private DocumentType docType;
    @NotBlank
    private String docNum;
    @NotBlank
    private String docIssuer;
    @Past
    @NotNull
    private LocalDate docIssueDate; 
    @Future
    @NotNull
    private LocalDate docExpiryDate;
}
