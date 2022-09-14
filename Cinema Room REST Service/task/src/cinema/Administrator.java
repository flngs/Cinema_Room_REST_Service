package cinema;

import java.util.Map;

public class Administrator {

    private final String password = "super_secret";
    private Map<String, Integer> stats;

    public Administrator(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(String name, Integer stat) {
        this.stats.put(name, stat);
    }
}
