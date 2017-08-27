package com.fbm.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "countries", schema = "fbm_db")
public class Country implements Comparable<Country>{
    private long countryId;
    private String countryName;
    private Set<Team> teams;

    @Id
    @Column(name = "country_id")
    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @OneToMany(mappedBy = "country")
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        if (!teams.contains(team)){
            teams.add(team);
            if (team.getCountry() != this){
                team.setCountry(this);
            }
        }
    }

    @Override
    public int compareTo(Country o) {
        return this.countryName.compareTo(o.countryName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return countryName.equals(country.countryName);
    }

    @Override
    public int hashCode() {
        return countryName.hashCode();
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
