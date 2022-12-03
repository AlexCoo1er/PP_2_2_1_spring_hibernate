package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void deleteAllUsers() {
      List<User> users = listUsers();
      for (User user: users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }
   @Override
   public User findUser(String carName, int carSeries) {
      TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :carName and series = :carSeries")
              .setParameter("carName", carName)
              .setParameter("carSeries", carSeries);
      List<Car> findCarList = findCarQuery.getResultList();
      if (!findCarList.isEmpty()) {
         Car findCar = findCarList.get(0);
         List<User> listUser = listUsers();
         User FindUser = listUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
         return FindUser;
      }
      return null;
   }
}
