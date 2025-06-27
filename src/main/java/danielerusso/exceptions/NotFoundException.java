package danielerusso.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(long id) {
        super("Nothing was found with this id: " + id);
    }
}
