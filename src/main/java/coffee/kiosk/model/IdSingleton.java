package coffee.kiosk.model;

public class IdSingleton {
    private static IdSingleton instance = null;
    private int Id;

    public IdSingleton() {
    }

    public static synchronized IdSingleton getInstance() {
        if (instance == null) {
            instance = new IdSingleton();
        }
        return instance;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
