public class EventResults {
    String edition;
    int edition_id;
    String country_noc;
    String sport;
    String event;
    int result_id;
    String athlete;
    int athlete_id;
    String pos;
    String medal;
    boolean isTeamSport;

    public EventResults(String edition, int editionId, String countryId, String sport, String event, int resultId, String athlete, int athleteId, String pos, String medal, boolean isTeamSport) {
        this.edition = edition;
        this.edition_id = editionId;
        this.country_noc = countryId;
        this.sport = sport;
        this.event = event;
        this.result_id = resultId;
        this.athlete = athlete;
        this.athlete_id = athleteId;
        this.pos = pos;
        this.medal = medal;
        this.isTeamSport = isTeamSport;
    }

    public EventResults() {
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getEdition_id() {
        return edition_id;
    }

    public void setEdition_id(int edition_id) {
        this.edition_id = edition_id;
    }

    public String getCountry_noc() {
        return country_noc;
    }

    public void setCountry_noc(String country_noc) {
        this.country_noc = country_noc;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public String getAthlete() {
        return athlete;
    }

    public void setAthlete(String athlete) {
        this.athlete = athlete;
    }

    public int getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public boolean isTeamSport() {
        return isTeamSport;
    }

    public void setTeamSport(boolean teamSport) {
        isTeamSport = teamSport;
    }

    @Override
    public String toString() {
        return "EventResults{" +
                "edition='" + edition + '\'' +
                ", edition_id=" + edition_id +
                ", country_noc='" + country_noc + '\'' +
                ", sport='" + sport + '\'' +
                ", event='" + event + '\'' +
                ", result_id=" + result_id +
                ", athlete='" + athlete + '\'' +
                ", athlete_id=" + athlete_id +
                ", pos='" + pos + '\'' +
                ", medal='" + medal + '\'' +
                ", isTeamSport=" + isTeamSport +
                '}';
    }
}
