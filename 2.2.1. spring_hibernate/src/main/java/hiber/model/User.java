package hiber.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @OneToOne
   @JoinColumn(name = "car_id")
   private Car userCar;

   public Car getCar() {
      return userCar;
   }

   public void setCar(Car car) {
      this.userCar = car;
   }

   public User() {}
   
   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.userCar = car;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "User{" +
              "id= " + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", car= " + userCar +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && email.equals(user.email);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, userCar);
   }
}
