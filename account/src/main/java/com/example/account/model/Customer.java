package com.example.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;
    String name;
    String surname;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<Account> accounts;

    public Customer(String name, String surname){
        this.id = "";
        this.name= name;
        this.surname = surname;
        this.accounts = new HashSet<>();
    }

    @Override
    public int hashCode(){
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object otherCustomer){
        Customer other = (Customer) otherCustomer;
        if (this == other)
            return true;
        if (!(otherCustomer instanceof Customer)) {
            return false;
        }
        if (this.id != other.id)
            return false;
        if (this.name != other.name)
            return false;
        if (this.surname != other.surname)
            return false;
        if (this.accounts != other.accounts)
            return false;

        return true;
    }
}
