package config;

public final class TestConfig {
    private TestConfig() {}

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://digital.harel-group.co.il/travel-policy");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static int timeoutSeconds() {
        String value = System.getProperty("timeoutSeconds", "15");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 15;
        }
    }
}
