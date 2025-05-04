package interpretador_simples;

public class Evaluator implements Expr.Visitor<Double> {
    private final Environment environment;

    public Evaluator(Environment environment) {
        this.environment = environment;
    }

    public Double evaluate(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public Double visitBinaryExpr(Expr.Binary expr) {
        double left = evaluate(expr.left);
        double right = evaluate(expr.right);
        switch (expr.operator.type) {
            case PLUS: return left + right;
            case MINUS: return left - right;
            case STAR: return left * right;
            case SLASH: return left / right;
            default: throw new RuntimeException("Unknown operator");
        }
    }

    @Override
    public Double visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Double visitLiteralExpr(Expr.Literal expr) {
        return (Double) expr.value;
    }

    @Override
    public Double visitVariableExpr(Expr.Variable expr) {
        return environment.getVariable(expr.name.lexeme);
    }

    @Override
    public Double visitAssignExpr(Expr.Assign expr) {
        double value = evaluate(expr.value);
        environment.setVariable(expr.name.lexeme, value);
        return value;
    }
}