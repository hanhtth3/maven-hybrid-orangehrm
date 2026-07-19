package utilitiles;

import net.datafaker.Faker;

import java.util.Locale;

public class DataConfigNet {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataConfigNet getData(){
        return new DataConfigNet();
    }

    public String getFirstName(){
        return faker.name().firstName();
    }
    public String getLastName(){
        return faker.name().lastName();
    }
    public String getUsername(){
        return faker.credentials().username();
    }
    public String getRandonNumber(){
        return String.valueOf(faker.number().randomDigit());
    }
    public String getPassword(){
        return faker.credentials().password(8,15,true,true,true);
    }
}

