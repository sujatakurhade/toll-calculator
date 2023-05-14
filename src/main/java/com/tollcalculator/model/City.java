package com.tollcalculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="city")
    private Set<CityHolidayCalendar> cityHolidayCalenders;

    @OneToOne(mappedBy = "city", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private CityWorkingCalendar cityWorkingCalender;

    @OneToOne(mappedBy = "city", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private CityHolidayMonthCalendar cityHolidayMonthCalender;

    @OneToMany(mappedBy="city")
    private Set<CityTaxRate> cityTaxRates;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "city_tax_exempt_vehicle",
            joinColumns= @JoinColumn(name = "city_id"),
            inverseJoinColumns= @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> taxExemptVehicles;

    @OneToOne(mappedBy="city",cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private CityTaxChoice cityTaxChoice;
}
