package danielerusso.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(long id) {
        super("No game was found with this id: " + id);
    }
}
