package atm.web.admin.model;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDetailsDTO {
    private int id; 
    private String firstName;
    private String lastName;
    private String middleName;
    private String citizenship;
    private String stateIsoCode;
    private String sex;
    private String birthDate;
    private String residenceAddress;
    private String actualAddress;
    private String email;
    private String phoneNumber;
    private String docType;
    private String docNum;
    private String docIssuer;
    private String docIssueDate;    
    private String docExpiryDate;
    private String createdAt;
    private String updatedAt;

    private List<AccountItemDTO> accounts;
}
