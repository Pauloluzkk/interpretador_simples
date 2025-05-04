package interpretador_simples;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Double> variables;

    public Environment() {
        this.variables = new HashMap<>();
    }

    public void setVariable(String name, double value) {
        if (!name.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            throw new RuntimeException("Invalid variable name: " + name);
        }
        variables.put(name, value);
    }

    public double getVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return variables.get(name);
    }

    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }
}