package atm.web.admin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerItemDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String stateIsoCode;
    private String sex;
    private String birthDate;
    private String residenceAddress;
    private String email;
    private String phoneNumber;
    private String docType;
    private String docNum;
    private String docIssuer;
}
