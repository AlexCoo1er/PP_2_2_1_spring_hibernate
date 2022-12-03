package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.deleteAllUsers();
      carService.deleteAllCars();

      carService.add(new Car("solaris", 2015));
      carService.add(new Car("honda", 1990));
      carService.add(new Car("mercedes", 2022));
      carService.add(new Car("Autovaz", 2007));
      List<Car> cars = carService.listCars();
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("User Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car "+user.getCar());
         System.out.println();
      }
      System.out.println();
      System.out.println("---------------------------------------------");
      System.out.println(userService.findUser("solaris", 2015));
      context.close();
   }
}
