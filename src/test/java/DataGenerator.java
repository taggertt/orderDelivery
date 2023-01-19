import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int change) {
        return LocalDate.now().plusDays(change).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String[] cities = new String[]{"Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород",
                "Биробиджан", "Благовещенск", "Брянск", "Великий  Новгород", "Владивосток", "Владикавказ",
                "Владимир", "Волгоград", "Вологда", "Воронеж", "Гатчина", "Горно-Алтайск", "Грозный",
                "Екатеринбург", "Иваново", "Ижевск", "Иркутск", "Йошкар-Ола", "Казань", "Калининград", "Калуга",
                "Кемерово", "Киров", "Кострома", "Красногорск", "Краснодар", "Красноярск", "Курган", "Курск",
                "Кызыл", "Липецк", "Магадан", "Магас", "Майкоп", "Махачкала", "Москва", "Мурманск", "Нальчик",
                "Нарьян-Мар", "Нижний Новгород", "Новосибирск", "Омск", "Орёл", "Оренбург", "Пенза", "Пермь",
                "Петрозаводск", "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань", "Салехард",
                "Самара", "Санкт-Петербург", "Санкт-Петербург", "Саранск", "Саратов", "Севастополь", "Симферополь",
                "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Улан-Удэ",
                "Ульяновск", "Уфа", "Хабаровск", "Ханты-Мансийск", "Чебоксары", "Челябинск", "Черкесск", "Чита",
                "Элиста", "Южно-Сахалинск", "Якутск", "Ярославль"};
        return cities[faker.number().numberBetween(0, cities.length) - 1];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().name();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    @Value
    public static class RegistrationInfo {
        String name;
        String city;
        String phone;

        public RegistrationInfo(String name, String city, String phone) {
            this.name = name;
            this.city = city;
            this.phone = phone;
        }

        public static RegistrationInfo generateInfo(String locale) {
            return new RegistrationInfo(generateName(locale), generateCity(locale), generatePhone(locale));
        }
    }
}