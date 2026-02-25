package javaFactory;

public class endUser {
    public  static void main(String[] args) {
        javaFactory.Honda hon = new javaFactory.Honda();
        hon.viewCar();
        hon.drivingCar();

        javaFactory.Huyndai huy = new javaFactory.Huyndai();
        huy.viewCar();
        huy.drivingCar();

        javaFactory.Ford fod = new javaFactory.Ford();
        fod.viewCar();
        fod.drivingCar();

        javaFactory.Toyota toy = new javaFactory.Toyota();
        toy.viewCar();
        toy.drivingCar();
    }
}
public javaFactory.Car getCar (String carName){
    javaFactory.Car car = null;
    switch (carName){
        case "Honda":
            car = new javaFactory.Car();
            break;
        case "Huydai":
            car = new javaFactory.Car();
            break;
        case "Ford";
            car = new javaFactory.Car();
            break;
        case "Toyota";
            car = new javaFactory.Car();
            break;
        default:
        throw new Exception("Car name is not suport");
    }
    return car;
}