package Library;

import java.util.ArrayList;
import java.util.List;

public class Database {
    List<User> users = new ArrayList<>();
    List<String> userNames = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        userNames.add(user.getName());
    }

    public int login(String email,String phoneNumber){
        int n = -1;
        for(User user : users){
            if (user.getEmail().matches(email) && user.getPhoneNumber().matches(phoneNumber)){
                n = users.indexOf(user);
                break;
            }
        }
        return n;
    }

    public User getUser(int n){
        return users.get(n);
    }
}
