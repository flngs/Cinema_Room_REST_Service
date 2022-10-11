package cinema.entity;

import java.util.Map;

public class Administrator {

    private final String password;
    private final Map<String, Integer> stats;

    public Administrator(String password, Map<String, Integer> stats) {
        this.password = password;
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
