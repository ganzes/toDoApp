package io.github.ganzes;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private final static String WELCOME = "Hello";
    private final static String FALLBACK_LANG_ID_WELCOME = "Hola";

    @Test
    public void testPrepareGreeting_nullName_GreetingWithFallback() throws Exception {
        //Given
        LangRepository mockRepository = alwaysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        //When
        String result = SUT.prepareGreeting(null, "-1");
        //Then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void testPrepareGreeting_name_GreetingWithName() throws Exception {
        //Givne
        HelloService SUT = new HelloService();
        String name = "test";
        //When
        String result = SUT.prepareGreeting(name, "-1");
        //Then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void testPrepareGreeting_nullLang_GreetingWithFallbackIdLang() throws Exception {
        //Given
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        //When
        String result = SUT.prepareGreeting(null, null);
        //Then
        assertEquals(FALLBACK_LANG_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void testPrepareGreeting_textLang_GreetingWithFallbackIdLang() throws Exception {
        //Given
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        //When
        String result = SUT.prepareGreeting(null, "abc");
        //Then
        assertEquals(FALLBACK_LANG_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception {
        //Given
        LangRepository mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };
        HelloService SUT = new HelloService(mockRepository);
        //When
        String result = SUT.prepareGreeting(null, "-1");
        //Then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, "Hola", null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang2() throws Exception {
        //Given
        LangRepository mockRepository = fallbackLangIdRepositoryEmpty();
        HelloService SUT = new HelloService(mockRepository);
        //When
        String result = SUT.prepareGreeting(null, null);
        //Then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepositoryEmpty() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };
    }
}