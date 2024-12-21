// Singleton class for central data collection
class WeatherDataCollector {
    private static WeatherDataCollector instance;

    private double temperature;
    private double humidity;
    private double pressure;

    // Private constructor to restrict instantiation
    private WeatherDataCollector() {}

    // Static method to provide a single instance
    public static synchronized WeatherDataCollector getInstance() {
        if (instance == null) {
            instance = new WeatherDataCollector();
        }
        return instance;
    }

    // Methods to set and get weather data
    public void setWeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}

// Information Expert for Temperature
class TemperatureExpert {
    public String analyzeTemperature(double temperature) {
        if (temperature < 0) {
            return "Freezing weather.";
        } else if (temperature < 20) {
            return "Cold weather.";
        } else if (temperature < 30) {
            return "Mild weather.";
        } else {
            return "Hot weather.";
        }
    }
}

// Information Expert for Humidity
class HumidityExpert {
    public String analyzeHumidity(double humidity) {
        if (humidity < 30) {
            return "Dry conditions.";
        } else if (humidity < 60) {
            return "Comfortable humidity.";
        } else {
            return "High humidity.";
        }
    }
}

// Information Expert for Pressure
class PressureExpert {
    public String analyzePressure(double pressure) {
        if (pressure < 1000) {
            return "Low pressure - Possible storms.";
        } else if (pressure < 1020) {
            return "Normal pressure.";
        } else {
            return "High pressure - Stable weather.";
        }
    }
}

// Main class to demonstrate the system
class WeatherMonitoringSystem {
    public static void main(String[] args) {
        // Get singleton instance
        WeatherDataCollector collector = WeatherDataCollector.getInstance();

        // Set weather data
        collector.setWeatherData(25.0, 55.0, 1015.0);

        // Use Information Experts to analyze data
        TemperatureExpert tempExpert = new TemperatureExpert();
        HumidityExpert humidityExpert = new HumidityExpert();
        PressureExpert pressureExpert = new PressureExpert();

        System.out.println("Weather Analysis:");
        System.out.println("Temperature: " + tempExpert.analyzeTemperature(collector.getTemperature()));
        System.out.println("Humidity: " + humidityExpert.analyzeHumidity(collector.getHumidity()));
        System.out.println("Pressure: " + pressureExpert.analyzePressure(collector.getPressure()));
    }
}

// Unit Tests
class WeatherMonitoringSystemTest {
    public static void main(String[] args) {
        WeatherDataCollector collector = WeatherDataCollector.getInstance();

        // Test 1: Singleton behavior
        WeatherDataCollector anotherCollector = WeatherDataCollector.getInstance();
        assert collector == anotherCollector : "Singleton test failed!";

        // Test 2: Setting and retrieving weather data
        collector.setWeatherData(10.0, 45.0, 1005.0);
        assert collector.getTemperature() == 10.0 : "Temperature test failed!";
        assert collector.getHumidity() == 45.0 : "Humidity test failed!";
        assert collector.getPressure() == 1005.0 : "Pressure test failed!";

        // Test 3: Temperature analysis
        TemperatureExpert tempExpert = new TemperatureExpert();
        assert tempExpert.analyzeTemperature(-5).equals("Freezing weather.") : "Temperature analysis failed for freezing weather!";
        assert tempExpert.analyzeTemperature(15).equals("Cold weather.") : "Temperature analysis failed for cold weather!";
        assert tempExpert.analyzeTemperature(25).equals("Mild weather.") : "Temperature analysis failed for mild weather!";
        assert tempExpert.analyzeTemperature(35).equals("Hot weather.") : "Temperature analysis failed for hot weather!";

        // Test 4: Humidity analysis
        HumidityExpert humidityExpert = new HumidityExpert();
        assert humidityExpert.analyzeHumidity(20).equals("Dry conditions.") : "Humidity analysis failed for dry conditions!";
        assert humidityExpert.analyzeHumidity(50).equals("Comfortable humidity.") : "Humidity analysis failed for comfortable humidity!";
        assert humidityExpert.analyzeHumidity(70).equals("High humidity.") : "Humidity analysis failed for high humidity!";

        // Test 5: Pressure analysis
        PressureExpert pressureExpert = new PressureExpert();
        assert pressureExpert.analyzePressure(990).equals("Low pressure - Possible storms.") : "Pressure analysis failed for low pressure!";
        assert pressureExpert.analyzePressure(1010).equals("Normal pressure.") : "Pressure analysis failed for normal pressure!";
        assert pressureExpert.analyzePressure(1030).equals("High pressure - Stable weather.") : "Pressure analysis failed for high pressure!";

        System.out.println("All tests passed!");
    }
}