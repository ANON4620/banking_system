package banking_system;

public class AccountNumberGenerator {
    private int nextId;

    public AccountNumberGenerator(int start) {
        this.nextId = start;
    }

    public int generate() {
        return nextId++;
    }
}