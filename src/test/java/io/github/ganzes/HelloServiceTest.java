package io.github.ganzes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private HelloService SUT = new HelloService();

    @Test
    public void testPrepareGreeting_null_GreetingWithFallback() throws Exception {
        //Givne + When
        String result = SUT.prepareGreeting(null);
        //Then
        assertEquals("Hello "+ HelloService.FALLBACK_NAME + "!", result); }

    @Test
    public void testPrepareGreeting_name_GreetingWithName() throws Exception {
        //Givne
        String name = "test";
        //When
        String result = SUT.prepareGreeting(name);
        //Then
        assertEquals("Hello "+ name + "!", result);
    }
}