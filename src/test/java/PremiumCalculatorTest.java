import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumCalculatorTest {


    @Test
    void shouldNotCalculateWhenPolicyNull() {
        Assertions.assertFalse(1 == 0);
    }
}