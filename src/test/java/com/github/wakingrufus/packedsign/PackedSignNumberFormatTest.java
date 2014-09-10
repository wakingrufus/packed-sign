package com.github.wakingrufus.packedsign;

import org.junit.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wakingrufus
 */
public class PackedSignNumberFormatTest {

    /**
     *
     */
    public PackedSignNumberFormatTest() {
    }

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testSomeMethod() {
        try {
            System.out.println("pack");
            NumberFormat nf = new PackedSignNumberFormat();
            Assert.assertEquals(12350L, nf.parse("1235{"));
            Assert.assertEquals(5564L, nf.parse("556D"));
            Assert.assertEquals("1235{", nf.format(12350L));
        } catch (ParseException ex) {
            Logger.getLogger(PackedSignNumberFormatTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
