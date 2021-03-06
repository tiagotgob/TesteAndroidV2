
package br.com.tgob.testeandroid.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAccount {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("agency")
    @Expose
    private String agency;
    @SerializedName("balance")
    @Expose
    private String balance;

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", agency='" + agency + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBalance() { return balance; }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
