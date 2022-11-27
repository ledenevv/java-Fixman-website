package com.example.application.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "services_list")
public class ServicesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", nullable = false)
    private Integer id;

    @Size(max = 60)
    @NotNull
    @Column(name = "service_name", nullable = false, length = 60)
    private String serviceName;

    @Size(max = 250)
    @Column(name = "service_descr", length = 250)
    private String serviceDescr;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "service")
    private Set<Order> orders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescr() {
        return serviceDescr;
    }

    public void setServiceDescr(String serviceDescr) {
        this.serviceDescr = serviceDescr;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", ServiceName='" + serviceName +
        ", ServiceDescr='" + serviceDescr +
        ", Price='" + price +
        '}';
    }

}