package com.example.eklecticproject.entity;


import com.example.eklecticproject.configuration.GrantedAuthorityDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Username;

    private String email;

    private String Password;
    @Column(name = "phonenumber")
    private String phonenumber;

    private String CodeVerification;
    private Integer loyaltyPoints;

    @Enumerated(EnumType.STRING)
    private Role Role;

    private LocalDateTime DateEndCode;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Token> user_id;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Abonnement> abonnements = new ArrayList<>();
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Role == null) {
            return new ArrayList<>();
        }
        return Role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
