package danielerusso.exceptions;

public class AlreadyInCollection extends Exception {
    public AlreadyInCollection() {
        super("This id is already linked to a game in your collection.");
    }
}
