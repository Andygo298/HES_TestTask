package com.github.andygo298.testTask.model;

import com.github.andygo298.testTask.model.enums.Role;
import com.github.andygo298.testTask.model.enums.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name", nullable = false, unique = true)
    @NonNull
    @Pattern(regexp = "^[a-zA-Z]{3,16}$", message = "Username must be in interval 3-16 characters.\n Latin letters only.")
    private String username;
    @Column(name = "password", nullable = false)
    @NotNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]{3,16}", message = "Password must be in interval 3-16 characters.\n Latin letters only and min 1 special symbol.")
    private String password;
    @Column(name = "first_name", nullable = false)
    @NonNull
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "First name must be in interval 1-16 characters.\n Latin letters only.")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NonNull
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "Last name must be in interval 1-16 characters.\n Latin letters only.")
    private String lastName;
    @Column(name = "is_active", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status isActive;
    @Column(name = "created_at", nullable = false, updatable = false)
    @NonNull
    private LocalDate createdAt;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @PrePersist
    public void prePersist() {
        password = new BCryptPasswordEncoder(8).encode(password);
    }

    //User details methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }

    //other
    public boolean isActive() {
        return isActive.toString().equals("ACTIVE");

    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
}
