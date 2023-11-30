package fmi.plovdiv.application.model;

public class CreateUserData {

    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String email;
    private final String password;

    public CreateUserData(String firstName, String lastName, String dateOfBirth, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
