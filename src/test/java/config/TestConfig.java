package config;

public final class TestConfig {
    private TestConfig() {}

    public static String baseUrl() {
        String value = System.getProperty("baseUrl");
        if (value == null || value.isBlank()) {
            return "https://digital.harel-group.co.il/travel-policy";
        }
        return value;
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
