package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void add(Car car);
    void deleteAllCars();
    List<Car> listCars();
}
