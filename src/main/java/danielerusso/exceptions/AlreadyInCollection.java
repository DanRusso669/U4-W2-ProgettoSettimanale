package danielerusso.exceptions;

public class AlreadyInCollection extends Exception {
    public AlreadyInCollection() {
        super("This game is already in your collection.");
    }
}
