package com.fbm.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mocart on 01-Aug-17.
 */
@Entity
@Table(name = "countries", schema = "fbm_db", catalog = "")
public class Country {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (countryId != country.countryId) return false;
        if (countryName != null ? !countryName.equals(country.countryName) : country.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (countryId ^ (countryId >>> 32));
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "country")
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", teams=" + teams +
                '}';
    }
}
