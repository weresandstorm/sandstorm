package io.sandstorm.controller.portadapter.persist.mgo;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "users", noClassnameStored = true)
public class User extends EntityOrBuilder {

    private String firstName;

    private String lastName;

    private Date created;

    private Gender gender;

    private int age;

    @Embedded
    private final List<Address> addresses = new ArrayList<Address>();

    @Embedded
    private Address mainAddress;

    @Reference
    private final List<User> friends = new ArrayList<User>();

    @Reference
    private User friend;

    public User() {
    }

    public User(String firstName, String lastName, User friend) {
        this(firstName, lastName);
        this.friend = friend;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName; this.lastName = lastName;
        this.created = new Date();
    }

    public User(String firstName, String lastName, Gender gender) {
        this.firstName = firstName; this.lastName = lastName;
        this.created = new Date();
        this.gender = gender;
    }


    public User(String firstName, String lastName, int age, Date created) {
        this.firstName = firstName; this.lastName = lastName; this.age = age; this.created = created;
    }

    public void modify(String firstName, String lastName, User friend) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        return gender == user.gender;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "TestUser [id=" + id() + ", firstName=" + firstName + ", lastName=" + lastName
                + "]";
    }

    public enum Gender { MALE, FEMALE }

}

