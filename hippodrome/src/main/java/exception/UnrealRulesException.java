package exception;

public class UnrealRulesException extends RuntimeException {

    public UnrealRulesException(String message){
        super(message);
    }

    public UnrealRulesException(){
        super();
    }
}
