package hexlet.code.status;

public final class Status {
    private final StatusType status;
    private final Object oldValue;
    private final Object newValue;

    public Status(StatusType statusType, Object oldV, Object newV) {
        status = statusType;
        oldValue = oldV;
        newValue = newV;
    }

    public StatusType getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
