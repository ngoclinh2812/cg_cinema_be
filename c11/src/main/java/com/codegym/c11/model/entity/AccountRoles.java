package com.codegym.c11.model.entity;

import com.codegym.c11.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore // Add this annotation to break the loop during JSON serialization
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public AccountRoles(Account account, ERole roleUser) {
    }

}
