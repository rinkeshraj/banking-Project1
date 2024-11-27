package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ACCOUNTS")
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HOLDER_FIRST_NAME", length = 255)
    @NonNull
    private String holderFirstName;

    @Column(name = "HOLDER_MIDDLE_NAME", length = 255)
    private String holderMiddleName;

    @Column(name = "HOLDER_LAST_NAME", length = 255)
    private String holderLastName;

    @Column(name = "EMAIL", unique = true, length = 100)
    @NonNull
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true, length = 10)
    @NonNull
    private String phoneNumber;

    @Column(name = "BALANCE")
    @NonNull
    private Double balance;

}
