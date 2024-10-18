public class Game {
    String edition;
    int edition_id;
    String edition_url;
    int year;
    String city;
    String country_flag_url;
    String country_noc;
    String start_date;
    String end_date;
    String isHeld;
    String competition_date;

    public Game(String edition, int editionId, String editionURL, int year, String city, String countryFlagURL, String countryID, String startDate, String endDate, String isHeld, String compStartDate) {
        this.edition = edition;
        this.edition_id = editionId;
        this.edition_url = editionURL;
        this.year = year;
        this.city = city;
        this.country_flag_url = countryFlagURL;
        this.country_noc = countryID;
        this.start_date = startDate;
        this.end_date = endDate;
        this.isHeld = isHeld;
        this.competition_date = compStartDate;
    }

    public Game() {
    }
}
