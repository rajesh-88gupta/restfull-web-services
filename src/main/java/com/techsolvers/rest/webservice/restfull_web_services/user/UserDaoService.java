package com.techsolvers.rest.webservice.restfull_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //JPA/Hibernate > Database
    // UserDao Service
    private static int userCount =0;
    private static List<User> users =new ArrayList<>();
//    static {
//        users.add(new User(++userCount,"Mohani", LocalDate.now().minusYears(24)));
//        users.add(new User(++userCount,"Rajesh", LocalDate.now().minusYears(34)));
//        users.add(new User(++userCount,"Brajesh", LocalDate.now().minusYears(31)));
//    }

    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
    public User findOne(int id){
        Predicate<? super User> predicate =user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public void deleteById(int id){
        Predicate<? super User> predicate =user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
