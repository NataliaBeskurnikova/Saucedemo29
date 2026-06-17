import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FirstTest {
    //КРАТНО 3, ВОЗВРАЩАТЬ 'Т'
    //КРАТНО 5, ВОЗВРАЩАТЬ 'M'
    //КРАТНО 3 И 5, ВОЗВРАЩАТЬ 'ТIM'
    //ВОЗВРАЩАТЬ 'FAIL'


    public String trialCode(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "ТIM";
        } else if (number % 5 == 0) {
            return "М";
        } else if (number % 3 == 0) {
            return "Т";
        } else return "FAIL";
    }

    @Test
    public void timTest() {
        String actualResult = trialCode(9);
        assertEquals(actualResult, "Т");
    }

    @Test
    public void timTest2() {
        String actualResult = trialCode(25);
        assertEquals(actualResult, "М");
    }

    @Test
    public void timTest3() {
        String actualResult = trialCode(15);
        assertEquals(actualResult, "ТIM");
    }

    @Test
    public void timTest4() {
        String actualResult = trialCode(2);
        assertEquals(actualResult, "FAIL");
    }
}

