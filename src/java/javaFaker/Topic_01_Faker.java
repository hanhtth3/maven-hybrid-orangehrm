import com.github.javafaker.Faker;

import java.util.Locale;

public class Topic_01_Faker {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("vi"));
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.internet().password());
        System.out.println(faker.internet().privateIpV4Address());
        System.out.println(faker.internet().publicIpV4Address());
        System.out.println(faker.internet().macAddress());
        System.out.println(faker.internet().ipV6Address());

        System.out.println(faker.address().city());
        System.out.println(faker.address().country());
        System.out.println(faker.address().streetAddress());
        System.out.println(faker.address().zipCode());

        System.out.println(faker.business().creditCardNumber());
        System.out.println(faker.business().creditCardType());
    }
}
