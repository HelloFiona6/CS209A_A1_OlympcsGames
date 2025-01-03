import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.io.BufferedReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class OlympicsAnalyzer implements OlympicsAnalyzerInterface {
    public ArrayList<Athlete> athletes = new ArrayList<>();
    public Map<Integer, Athlete> athletesMap = new HashMap<>();
    public ArrayList<Medal> medals = new ArrayList<>();
    public ArrayList<EventResults> eventResults = new ArrayList<>();
    public ArrayList<Game> games = new ArrayList<>();
    public Map<Integer, Game> gameMap = new HashMap<>();
//    public ArrayList<Result> results = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    public Map<String, String> countryMap = new HashMap<>();


    public OlympicsAnalyzer(String datasetPath) {
        athletes = parseCSV(Paths.get(datasetPath, "Olympic_Athlete_Bio_filtered.csv").toString(), Athlete.class);
        for (Athlete a : athletes) {
            athletesMap.put(a.athlete_id, a);
        }
        medals = parseCSV(Paths.get(datasetPath, "Olympic_Games_Medal_Tally.csv").toString(), Medal.class);
        eventResults = parseCSV(Paths.get(datasetPath, "Olympic_Athlete_Event_Results.csv").toString(), EventResults.class);
        games = parseCSV(Paths.get(datasetPath, "Olympics_Games.csv").toString(), Game.class);
        for (Game g : games) {
            gameMap.put(g.edition_id, g);
        }
//        results = parseCSV(Paths.get(datasetPath, "Olympic_Results.csv").toString(), Result.class);
        countries = parseCSV(Paths.get(datasetPath, "Olympics_Country.csv").toString(), Country.class);
        for (Country c : countries) {
            countryMap.put(c.noc, c.country);
        }
    }

    public <T> ArrayList<T> parseCSV(String filePath, Class<T> clazz) {
        ArrayList<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                return result;
            }
            String[] headers = headerLine.split(",");
            String line;
            while ((line = readCSVLine(reader)) != null) {
                String[] values = splitCSVLine(line);
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    String fieldName = headers[i].trim();
                    String value = values[i].trim();
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    if (value.isEmpty()) {
                        setDefaultValue(obj, field);
                    } else {
                        setFieldValue(obj, field, value);
                    }
                }
                result.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readCSVLine(BufferedReader reader) throws IOException {
        StringBuilder line = new StringBuilder();
        boolean insideQuote = false;
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            line.append(nextLine);
            long quoteCount = nextLine.chars().filter(ch -> ch == '"').count();
            if (quoteCount % 2 != 0) {  // Odd number of quotes, still inside a quoted field
                insideQuote = !insideQuote;
                line.append("\n");
            } else if (!insideQuote) {
                break;
            }
        }
        return line.length() == 0 ? null : line.toString();
    }

    private String[] splitCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean insideQuote = false;
        StringBuilder token = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            } else if (c == ',' && !insideQuote) {
                tokens.add(token.toString());
                token.setLength(0);
            } else {
                token.append(c);
            }
        }
        tokens.add(token.toString());  // Add the last token
        return tokens.toArray(new String[0]);
    }

    private <T> void setDefaultValue(T obj, Field field) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type == float.class) {
            field.setFloat(obj, 0.0f);
        } else if (type == int.class) {
            field.setInt(obj, 0);
        } else if (type == boolean.class) {
            field.set(obj, true);
        } else {
            field.set(obj, null);
        }
    }

    private <T> void setFieldValue(T obj, Field field, String value) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type == float.class) {
            field.setFloat(obj, Float.parseFloat(value));
        } else if (type == int.class) {
            field.setInt(obj, Integer.parseInt(value));
        } else if (type == boolean.class) {
            field.set(obj, Boolean.parseBoolean(value));
        } else {
            field.set(obj, value);
        }
    }

    /**
     * Find the top 10 performing female athletes in each sport which was not team sport
     *
     * @return
     */
    @Override
    public Map<String, Integer> topPerformantFemale() {
        Map<String, Long> top = eventResults.stream()
                .filter(e -> !e.isTeamSport) // Filter out team sports
                .filter(e -> e.medal != null && e.medal.equals("Gold")) // Only gold medals
                .filter(e -> athletesMap.get(e.athlete_id) != null && athletesMap.get(e.athlete_id).sex.equals("Female")) // Only female athletes
                .collect(Collectors.groupingBy(e -> e.athlete, Collectors.counting())); // Count gold medals per athlete

        // Sort and limit to the top 10 performers
        return top.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    int countComparison = Long.compare(entry2.getValue(), entry1.getValue());
                    if (countComparison != 0) {
                        return countComparison;
                    } else {
                        return entry1.getKey().compareTo(entry2.getKey());
                    }
                }).limit(10) // Top 10 performers
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().intValue(), // Convert Long to Integer
                        (e1, e2) -> e1, // Handle potential key conflicts (not expected here)
                        LinkedHashMap::new // Maintain insertion order
                ));
    }


    @Override
    public Map<String, Float> bmiBySports() {
        return eventResults.stream()
                .map(result -> {
                    Athlete athlete = athletesMap.get(result.getAthlete_id());
                    if (athlete != null) {
                        float height = athlete.getHeight() / 100;
                        float weight = athlete.getWeight();
                        if (height > 0 && weight > 0) {
                            float bmi = weight / (height * height);
                            return new AbstractMap.SimpleEntry<>(result.getSport(),
                                    new AbstractMap.SimpleEntry<>(athlete.getAthlete_id(), bmi));
                        }
                    }
                    return null; // Filter out nulls later
                })
                .filter(Objects::nonNull) // Remove null entries
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.toMap(
                                entry -> entry.getValue().getKey(),
                                entry -> entry.getValue().getValue(),
                                (bmi1, bmi2) -> bmi1
                        )
                ))
                .entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        Math.round(entry.getValue().values().stream().mapToDouble(Float::doubleValue).average().orElse(0) * 10) / 10.0f)) // Round to one decimal place
                .sorted(Map.Entry.<String, Float>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    @Override
    public Map<String, Set<Integer>> leastAppearedSport() {
        return eventResults.stream()
                .filter(a->a.edition.contains("Summer"))
                .collect(Collectors.groupingBy(
                        a -> a.sport,
                        Collectors.mapping(a -> gameMap.get(a.edition_id).year, Collectors.toSet())
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Set<Integer>>comparingByValue(
                                Comparator.comparingInt(Set::size))
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, Integer> winterMedalsByCountry() {
        return medals.stream()
                .filter(m -> m.year >= 2000)
                .filter(m -> m.edition.contains("Winter"))
                .collect(Collectors.groupingBy(Medal::getCountry_noc, Collectors.summingInt(Medal::getTotal)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, Integer> topCountryWithYoungAthletes() {
        Set<Integer> processedAthleteIds = new HashSet<>();

        return eventResults.stream()
                .filter(a -> a.edition_id == 61) // 2020 summer
                .filter(a -> athletesMap.get(a.athlete_id) != null && athletesMap.get(a.athlete_id).born != null)
                .filter(a -> processedAthleteIds.add(a.athlete_id))
                .collect(Collectors.groupingBy(
                        a -> countryMap.get(a.country_noc),
                        Collectors.averagingDouble(a -> 2020 - Integer.parseInt(athletesMap.get(a.athlete_id).born.substring(athletesMap.get(a.athlete_id).born.length() - 4)))
                ))
                .entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), Math.round(entry.getValue().floatValue()))) // Round the average age
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
