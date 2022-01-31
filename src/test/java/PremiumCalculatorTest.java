import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PremiumCalculatorTest {

    private PremiumCalculator underTest;

    @Test
    void shouldNotCalculateWithNoObjects() {
        // given
        Policy testPolicy = new Policy();
        // then
        Assertions.assertThrows(RuntimeException.class, () -> underTest.calculate(testPolicy));
    }

    @Test
    void shouldNotCalculateWithNoSubobjects() {
        // given
        Policy testPolicy = new Policy();
        Object testObject = new Object("House");
        Object.addObjectToPolicy(testPolicy, testObject);
        // then
        Assertions.assertThrows(RuntimeException.class, () -> underTest.calculate(testPolicy));
    }

    @Test
    void shouldNotCreateObjectWithNoName() {
        Assertions.assertThrows(Exception.class, () -> {
            Object testObject = new Object();
        });
    }

    /**
     * tests if calculator works correctly.
     * currently the method PremiumCalculator.calculate(Policy policy)
     * doesnt return or store the calculated premium,
     * but uses system output stream to display it.
     *
     */
    @Test
    void shouldCalculatePremiumCorrectly1() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Policy p1 = new Policy();
        Object o1 = new Object("Condo");
        Object.addObjectToPolicy(p1, o1);

        Subobject s1 = new Subobject("Monitor",100.00F,"FIRE");
        Subobject.addSubobjectToObject(o1, s1);
        Subobject s2 = new Subobject("Phone charger", 8.00F, "THEFT");
        Subobject.addSubobjectToObject(o1, s2);
        // when
        underTest.calculate(p1);
        String premium = outputStream.toString();
        // then
        Assertions.assertTrue(premium.contains("premium is 2.28 EUR"));
    }

    @Test
    void shouldCalculatePremiumCorrectly2() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Policy p1 = new Policy();
        Object o2 = new Object("Private garage");
        Object.addObjectToPolicy(p1, o2);

        Subobject s3 = new Subobject("Fridge",500.00F, "FIRE");
        Subobject.addSubobjectToObject(o2, s3);
        Subobject s4 = new Subobject("Power drill", 102.51F, "THEFT");
        Subobject.addSubobjectToObject(o2, s4);
        // when
        underTest.calculate(p1);
        String premium = outputStream.toString();
        // then
        Assertions.assertTrue(premium.contains("premium is 17.13 EUR"));
    }


}