package com.github.igorek2312.blog.app.model;

import com.github.igorek2312.blog.app.reference.Constants;
import com.github.igorek2312.blog.app.service.validation.ConfirmPasswordConstraint;
import com.github.igorek2312.blog.app.service.validation.UniqueEmail;
import com.github.igorek2312.blog.app.service.validation.UniqueUsername;
import com.github.igorek2312.blog.app.transfer.user.ConfirmPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "user")
@ConfirmPasswordConstraint
public class User implements ConfirmPassword {
    @Id
    @GeneratedValue
    private Integer id;

    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Length(min = 1, max = 100)
    @NotBlank
    @UniqueUsername
    @Column(name = "username", length = 100, unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String passwordHash;

    @Length(min = 4, max = 30)
    @NotBlank
    @Transient
    private String password;

    @Transient
    private String confirmedPassword;

    @Length(max = 50)
    @NotBlank
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Length(max = 50)
    @NotBlank
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @UniqueEmail
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "activated", nullable = false)
    private boolean activated = false;

    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Column(name = "activation_key", length = 36)
    private String activationKey;

    @Column(name = "reset_key", length = 36)
    private String resetKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.passwordHash = user.passwordHash;
        this.activated = user.activated;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.imageUrl = user.imageUrl;
        this.roles = user.roles;
    }

    public void encodePassword(Function<String, String> encoder) {
        passwordHash = encoder.apply(password);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean isConfirmedPasswordValid() {
        return (password == null && confirmedPassword == null) || password.equals(confirmedPassword);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
