package umc.spring.apiPayload.exception;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException(String message) {
        super(message);
    }
}
