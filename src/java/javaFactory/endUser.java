package javaFactory;

public class endUser {
    public public static void main(String[] args) {
        Honda hon = new Honda();
        hon.viewCar();
        hon.drivingCar();

        Huyndai huy = new Huyndai();
        huy.viewCar();
        huy.drivingCar();

        Ford fod = new Ford();
        fod.viewCar();
        fod.drivingCar();

        Toyota toy = new Toyota();
        toy.viewCar();
        toy.drivingCar();
    }


}
public Car getCar (String carName){
    Car car = null;
    switch (carName){
        case "Honda";
            car = new Honda();
            break;
        case "Huydai";
            car = new Huyndai();
            break;
        case "Ford";
            car = new Ford();
            break;
        case "Toyota";
            car = new Toyota();
            break;
        default:
        throw new Exception("Car name is not suport");
    }
    return car;
}