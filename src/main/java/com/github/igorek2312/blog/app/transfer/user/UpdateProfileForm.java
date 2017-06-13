package com.github.igorek2312.blog.app.transfer.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Igor Rybak
 */
public class UpdateProfileForm {
    @Length(max = 50)
    @NotBlank
    private String firstName;

    @Length(max = 50)
    @NotBlank
    private String lastName;

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
}
