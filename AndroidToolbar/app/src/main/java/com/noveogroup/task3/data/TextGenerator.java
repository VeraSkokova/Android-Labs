package com.noveogroup.task3.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class TextGenerator {
    private static final List<String> STRING_LIST = new ArrayList<>();
    private static final Random RANDOM = new Random();

    static {
        STRING_LIST.add("Moscow");
        STRING_LIST.add("London");
        STRING_LIST.add("Paris");
        STRING_LIST.add("New York");
        STRING_LIST.add("Hong Kong");
        STRING_LIST.add("Berlin");
        STRING_LIST.add("Barcelona");
        STRING_LIST.add("Rome");
        STRING_LIST.add("Saint Petersburg");
        STRING_LIST.add("Novosibirsk");
        STRING_LIST.add("Beijing");
        STRING_LIST.add("Tokio");
        STRING_LIST.add("Bangkok");
        STRING_LIST.add("Kuala Lumpur");
        STRING_LIST.add("Rio de Janeiro");
        STRING_LIST.add("Lisbon");
        STRING_LIST.add("Prague");
        STRING_LIST.add("Warsaw");
        STRING_LIST.add("Milan");
        STRING_LIST.add("Athens");
        STRING_LIST.add("Jakarta");
        STRING_LIST.add("Astana");
        STRING_LIST.add("Oslo");
        STRING_LIST.add("Stockholm");
        STRING_LIST.add("Helsinki");
        STRING_LIST.add("Buenos Aires");
    }

    public static List<String> getStringList() {
        return STRING_LIST;
    }

    public static String getNewCity() {
        return STRING_LIST.get(RANDOM.nextInt(STRING_LIST.size()));
    }
}
