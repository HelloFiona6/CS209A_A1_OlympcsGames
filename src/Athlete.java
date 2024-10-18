public class Athlete {
    int athlete_id;
    String name;
    String sex;
    String born;
    float height;
    float weight;
    String country;
    String country_noc;
    String description;
    String special_notes;

    public Athlete(int athleteID, String name, String sex, String born, float height, float weight, String country, String countryCode, String description, String specialNote) {
        this.athlete_id = athleteID;
        this.name = name;
        this.sex = sex;
        this.born = born;
        this.height = height;
        this.weight = weight;
        this.country = country;
        this.country_noc = countryCode;
        this.description = description;
        this.special_notes = specialNote;
    }
    public Athlete() {
    }

    public int getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_noc() {
        return country_noc;
    }

    public void setCountry_noc(String country_noc) {
        this.country_noc = country_noc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecial_notes() {
        return special_notes;
    }

    public void setSpecial_notes(String special_notes) {
        this.special_notes = special_notes;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "athlete_id=" + athlete_id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", born='" + born + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", country='" + country + '\'' +
                ", country_noc='" + country_noc + '\'' +
                ", description='" + description + '\'' +
                ", special_notes='" + special_notes + '\'' +
                '}';
    }
}
