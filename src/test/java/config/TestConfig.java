package config;

public final class TestConfig {
    private TestConfig() {}

    // Read once at JVM startup for deterministic behavior across parallel threads.
    private static final boolean DEBUG_FLOW_ENABLED =
            Boolean.parseBoolean(System.getProperty("debugFlow", "false"));

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
        String value = System.getProperty("timeoutSeconds", "5");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 5;
        }
    }

    public static boolean debugFlow() {
        return DEBUG_FLOW_ENABLED;
    }
}
