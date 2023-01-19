import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

 class TestDelivery {

    @BeforeEach
    void setup () {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulTwoMeetings() {
        var userInfo = DataGenerator.RegistrationInfo.generateInfo("ru");
        var daysAddToFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysAddToFirstMeeting);
        var daysAddToSecMeeting = 10;
        var secMeetingDate = DataGenerator.generateDate(daysAddToSecMeeting);
        $("[placeholder='Город']").setValue(userInfo.getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(firstMeetingDate);
        $("[name='name']").setValue(userInfo.getName());
        $("[name='phone']").setValue(userInfo.getPhone());
        $(".checkbox__box").click();
        $$("[role='button']").find(exactText("Запланировать")).click();
        $("[data-test-id='success-notification']  .notification__title")
                .shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification']  .notification__content")
                .shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(secMeetingDate);
        $$("[role='button']").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification']  .notification__title").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Необходимо подтверждение"));
        $$("[data-test-id='replan-notification'] button").find(exactText("Перепланировать")).click();
        $("[data-test-id='success-notification']  .notification__title").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification']  .notification__content").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(exactText("Встреча успешно запланирована на " + secMeetingDate));
    }
}


