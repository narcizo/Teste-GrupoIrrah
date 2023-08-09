package com.narcizo.TesteIrrah.entity;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String cnpj;
    private String companyName;

    @ManyToOne(targetEntity = PaymentPlan.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_plan_id", referencedColumnName = "id")
    private PaymentPlan paymentPlan;

    @ElementCollection // 1
    @CollectionTable(name = "user_phone_numbers", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "user_phone_number") // 3
    private List<String> userPhoneNumbers;

    public Client(long id,
                  String name,
                  String email, String phone,
                  String cpf, String cnpj,
                  String companyName,
                  PaymentPlan paymentPlan,
                  List<String> userPhoneNumbers
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.paymentPlan = paymentPlan;
        this.userPhoneNumbers = userPhoneNumbers;
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public List<String> getUserPhoneNumbers() {
        return userPhoneNumbers;
    }

    public void setUserPhoneNumbers(List<String> userPhoneNumbers) {
        this.userPhoneNumbers = userPhoneNumbers;
    }
}
