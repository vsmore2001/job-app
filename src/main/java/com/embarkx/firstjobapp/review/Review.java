package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

    // constructor
    public Review() {
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // toString
    @Override
    public String toString() {
        return "Review [id=" + id + ", title=" + title + ", description=" +
                description + ", rating=" + rating + "]";
    }
}