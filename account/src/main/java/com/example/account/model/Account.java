package com.example.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;
    BigDecimal balance;
    LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Transaction> transactions = new HashSet<>();

    public Account (Customer customer, BigDecimal balance, LocalDateTime date){
        this.id = "";
        this.customer = customer;
        this.balance = balance;
        this.date = date;
    }

    @Override
    public int hashCode(){
        int result = id.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + customer.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account other = (Account) o;
        return this.id.equals(other.id)
                && this.balance.equals(other.balance)
                && this.date.equals(other.date);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", date=" + date +
                ", customer=" + customer +
                ", transactions=" + transactions +
                '}';
    }
}
