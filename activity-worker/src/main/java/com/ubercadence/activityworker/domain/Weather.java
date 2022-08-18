package com.ubercadence.activityworker.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cityName;

    private Double temperature;

    @Column(updatable = false)
    private String creationDate;

    public Weather() {
    }

    public Weather(String cityName, double temperature, String creationDate) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.creationDate = creationDate;
    }

}
