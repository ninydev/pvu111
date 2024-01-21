package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class User_NoLomBok {
    private User_NoLomBok(){
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public User_NoLomBok(String name)
    {
        this();
        this.name = name;
    }

    public User_NoLomBok(String name, String email, String password)
    {
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

    public void setPassword(String password) {
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
