package atm.web.admin.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerEditDTO {
    private int id;
    @NotBlank(message = "First name is required")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Residence Address is required")
    private String residenceAddress;
    @NotBlank(message = "Actual Address is required")
    private String actualAddress;
    @Email(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone format: xxxxxxxxxx")
    private String phoneNumber;
}
