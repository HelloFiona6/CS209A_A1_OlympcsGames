public class Medal {
    String edition;
    int edition_id;
    int year;
    String country;
    String country_noc;
    int gold;
    int silver;
    int bronze;
    int total;

    public Medal(String edition, int editionId, int year, String country, String countryCode, int gold, int silver, int bronze, int total) {
        this.edition = edition;
        this.edition_id = editionId;
        this.year = year;
        this.country = country;
        this.country_noc = countryCode;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.total = total;
    }

    public Medal() {
    }

    public String getEdition() {
        return edition;
    }

    public int getTotal() {
        return total;
    }

    public int getBronze() {
        return bronze;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }

    public String getCountry_noc() {
        return country_noc;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public int getEdition_id() {
        return edition_id;
    }
}
