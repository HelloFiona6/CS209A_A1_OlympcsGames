public class Country {
    String noc;
    String country;

    public Country(String countryID, String country) {
        this.noc = countryID;
        this.country = country;
    }

    public Country() {
    }

    public String getNoc() {
        return noc;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_noc='" + noc + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
