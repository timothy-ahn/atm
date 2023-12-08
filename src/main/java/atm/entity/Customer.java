package atm.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = 
{ 
    @UniqueConstraint(name = "UniqueResident", columnNames = { "state_iso_code", "doc_num" })
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @NotBlank
    private String citizenship;
    @NotBlank
    @Column(name = "state_iso_code", length = 3)
    @Pattern(regexp = "^[A-Za-z]{3}$")
    private String stateIsoCode;
    @NotNull
    private String sex;
    @NotNull
    @Past
    private LocalDate birthDate;
    @NotBlank
    @Size(min = 2, max = 255)
    private String residenceAddress;
    @NotBlank
    @Size(min = 2, max = 255)
    private String actualAddress;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(length = 15, unique = true)
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private DocumentType docType;
    @NotBlank
    @Column(name = "doc_num", length = 30)
    private String docNum;
    @NotBlank
    @Size(min = 2, max = 100)
    private String docIssuer;
    @NotNull
    @Past
    private LocalDate docIssueDate;
    @NotNull
    @Future
    private LocalDate docExpiryDate;
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    public enum DocumentType {
        PASSPORT,
        ID_CARD
    }
}
