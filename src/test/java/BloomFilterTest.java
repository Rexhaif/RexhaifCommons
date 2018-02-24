import org.junit.*;
import rexhaif.commons.utils.BloomFilter;

public class BloomFilterTest {

    @Test
    public void bloomFilterGetTest() {

        BloomFilter<String> filter = new BloomFilter<>(1000);
        for (int i = 0; i < 1000; i++) {

            filter.add("" + i);

        }

        for (int i = 0; i < 1000; i++) {

            Assert.assertTrue(filter.contains("" + i));

        }

    }
}
