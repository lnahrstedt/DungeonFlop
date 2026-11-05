package resources.exceptions;

import resources.interfaces.StringsammlungException;

public class SolException extends Exception {

    public SolException() {
        super(StringsammlungException.SOL_EXCEPTION);
    }
}
