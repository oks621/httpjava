package ua.http;

import java.util.List;
import java.util.Objects;

public class Adress {

    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;

    public Adress(String street, String suite, String city, String zipcode, Geo geo) {

        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + "', geo{" + geo + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(geo, adress.geo) && Objects.equals(street, adress.street) && Objects.equals(suite, adress.suite) && Objects.equals(city, adress.city) && Objects.equals(zipcode, adress.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(geo, street, suite, city, zipcode);
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
