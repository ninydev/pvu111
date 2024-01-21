package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.exceptions.PasswordLengthException;
import org.example.exceptions.PasswordRegexException;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class User {

    private Group group;

    private User(){
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public User(String name)
    {
        this();
        this.name = name;
    }

    public User(String name, String email, String password) throws PasswordLengthException, PasswordRegexException {
        this(name);
        this.email = email;
        // this.password = password;
        setPassword(password);
    }

    @Override
    public String toString() {
        return super.toString() + " => { \n" +
                "id: " + this.id + "\n" +
                "name: " + this.name + "\n" +
                "age: " + this.age + "\n" +
                "email: " + email + "\n" +
                "password: " + password + "\n" +
                "updatedAt: " + this.updatedAt.toString() + "\n" +

                "} \n"
                ;
    }

    private UUID id;
    private String name;
    private int age;
    private String email;
    private String password;

    private static final String PASSWORD_PATTERN =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\p{Punct}]).*$";

    public void setPassword(String password) throws PasswordLengthException, PasswordRegexException {
        if(password.length() < 3) {
            // Как написать код так, что бы пользователь в таком случае не создавался
            throw new PasswordLengthException("password.length");
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new PasswordRegexException("password.regex");
        }

        this.password = "MD5(" + password + ")";
    }

    public String getPassword() {
        return password;
    }

    private Date createdAt;
    private Date updatedAt;

    /*

    // Плохая практика - даватьп рямой доступ к полям класса

    public UUID id;
    public String name;
    public int age;
    public String email;
    public String password;
    public Date createdAt;
    public Date updatedAt;
     */
}
