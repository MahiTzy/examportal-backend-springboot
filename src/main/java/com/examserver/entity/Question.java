package com.examserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;
    @Column(length = 5000)
    private String content;
    private String image;
    @Column(length = 500)
    private String option1;
    @Column(length = 500)
    private String option2;
    @Column(length = 500)
    private String option3;
    @Column(length = 500)
    private String option4;

    @Column(length = 500)
    private String answer;

    @Transient
    private String givenAnswer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
