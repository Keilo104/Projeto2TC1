package Faker;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class SuperpoderFakerUtil {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static String getValidNome(){
        return String.format("%s %s %s",
                faker.resolve("games.pokemon.moves"),
                StringUtils.capitalize(faker.resolve("lovecraft.words")),
                faker.superhero().suffix()
        );
    }

    public static String getValidDescricao(){
        String control = random.nextBoolean() ? "" : " no";

        return String.format("User is able to %s, but only %d times every %d months, and they have%s control over it.",
                faker.superhero().power(),
                random.nextInt(6)+2,
                random.nextInt(16)+3,
                control
        );
    }

    public static String getValidEfeitosColaterais(){
        return String.format("Whenever user %s near a %s, user shouts \"%s\" uncontrollably.",
                faker.resolve("verbs.simple_present"),
                faker.resolve("appliance.equipment"),
                faker.resolve("rick_and_morty.quotes")
        );
    }

    public static String getValidNota() {
        return Integer.toString(random.nextInt(5) + 1);
    }

}
