import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest {
    MainClass mc = new MainClass();

    @Test
    public void testGetLocalNumber()
    {

        Assert.assertTrue("LocalNumber does not equal 14", mc.getLocalNumber()==14);
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("ClassNumber is less or equal 45", mc.getClassNumber()>45);
    }
}
