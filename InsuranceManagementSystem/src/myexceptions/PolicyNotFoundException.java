package myexceptions;

import java.io.Serializable;

public class PolicyNotFoundException extends Exception implements Serializable {
   
    private static final long serialVersionUID = 1L;

    public PolicyNotFoundException(String message) {
        super(message);
    }
}
