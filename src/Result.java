public class Result {
    int result_id;
    String event_title;
    String edition;
    int edition_id;
    String sport;
    String sport_url;
    String result_date;
    String result_location;
    String result_participants;
    String result_format;
    String result_detail;
    String result_description;

    public Result(int result_id, String event_title, String edition, int edition_id, String sport, String sport_url, String result_date, String result_location, String result_participants, String result_format, String result_detail, String result_description) {
        this.result_id = result_id;
        this.event_title = event_title;
        this.edition = edition;
        this.edition_id = edition_id;
        this.sport = sport;
        this.sport_url = sport_url;
        this.result_date = result_date;
        this.result_location = result_location;
        this.result_participants = result_participants;
        this.result_format = result_format;
        this.result_detail = result_detail;
        this.result_description = result_description;
    }

    public Result() {
    }

    public int getResult_id() {
        return result_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public String getEdition() {
        return edition;
    }

    public int getEdition_id() {
        return edition_id;
    }

    public String getSport() {
        return sport;
    }

    public String getSport_url() {
        return sport_url;
    }

    public String getResult_location() {
        return result_location;
    }

    public String getResult_participants() {
        return result_participants;
    }

    public String getResult_format() {
        return result_format;
    }

    public String getResult_detail() {
        return result_detail;
    }

    public String getResult_description() {
        return result_description;
    }

}
