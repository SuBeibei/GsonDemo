import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ExposureExample {
    public static void main(String[] args) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        final Account account = new Account();
        account.accountNumber = "A123 45678 90";
        account.iban = "IBAN 11 22 33 44";
        account.owner = "Albert Attard";
        account.pin = "1234";
        account.address = "Somewhere, Far Far Away";

        System.out.printf("%s%n", gson.toJson(account));

        final String accountStr = "{\"accountNumber\":\"A123 45678 90\",\"iban\":\"IBAN 11 22 33 44\",\"owner\":\"Albert Attard\",\"address\":\"Somewhere, Far Far Away\",\"pin\":\"1234\"}";
        final Account otherAccount = gson.fromJson(accountStr, Account.class);
        System.out.println(otherAccount);
    }

    static class Account {
        @Expose(deserialize = false)
        public String accountNumber;

        @Expose
        public String iban;

        @Expose(serialize = false)
        public String owner;

        @Expose(serialize = false, deserialize = false)
        public String address;

        public String pin;

        @Override
        public String toString() {
            return  "accountNumber: " + accountNumber + "\n" +
                    "iban: " + iban + "\n" +
                    "owner: " + owner + "\n" +
                    "address: " + address + "\n" +
                    "pin: " + pin;
        }
    }
}