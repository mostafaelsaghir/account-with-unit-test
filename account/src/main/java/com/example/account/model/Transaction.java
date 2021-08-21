package com.example.account.model;

import com.example.account.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id ;
    TransactionType transactionType;
    BigDecimal amount;
    LocalDateTime date ;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    public Transaction (BigDecimal amount, LocalDateTime date, Account account){
        this.id = null;
        this.amount = amount;
        this.date = date;
        this.transactionType = TransactionType.INITIAL;
        this.account = account;
    }

    @Override
    public int hashCode(){
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + transactionType.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction other = (Transaction) o;
        return this.id.equals(other.id)
                && this.transactionType.equals(other.transactionType)
                && this.amount.equals(other.amount)
                && this.date.equals(other.date)
                && this.account.equals(other.account);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", date=" + date +
                ", account=" + account +
                '}';
    }
}
