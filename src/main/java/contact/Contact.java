
package contact;

public class Contact {
    private final String name;
    private String phoneNumber;

    public Contact(String name) {
        this.name = name;
        this.phoneNumber = null;

    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void updateContactNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String storageString() {
        return this.name + " " + this.phoneNumber;
    }

    public String toString() {
        return this.name + " " + this.phoneNumber;
    }


}