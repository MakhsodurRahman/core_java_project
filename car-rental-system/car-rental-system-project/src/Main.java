import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carId;
    private String brand;
    private String model;
    private Double basePricePerDay;
    private Boolean isAvailable;

    Car(String carId,String brand,String model,Double basePricePerDay){
        this.carId = carId;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.brand = brand;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }
    public Boolean isAvailable() {
        return isAvailable;
    }
}

class Customer{
    private String customerId;
    private String name;
    Customer(String customerId, String  name){
        this.customerId = customerId;
        this.name  = name;
    }

    public String getCustomerId(){
        return customerId;
    }
    public String getName(){
        return name;
    }
}

class Rental{
    private Car car;
    private Customer customer;
    private Integer days;

    Rental(Car car,Customer customer,Integer days){
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getDays() {
        return days;
    }
}

class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rendCar(Car car, Customer customer, Integer days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else {
            System.out.println(car.getCarId() + " car is not available for rent");
        }
    }

    public void returnCar(Car car){

        Rental rentalToRemove = null;
        for (Rental rental : rentals){
            if (rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println("Car return successfully");
            car.returnCar();
        }else {
            System.out.println("Car was not rent");
        }
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("======== Car Rental System");
            System.out.println("1. Rent a car ");
            System.out.println("2. Return a car ");
            System.out.println("3. Exit");

            int choose = scanner.nextInt();
            scanner.nextLine();
            if(choose == 1){
                System.out.println("\n == Rent a car ==\n");
                System.out.println("Enter your name");
                String customerName = scanner.nextLine();

                System.out.println("\n available cars ");
                for (Car car : cars){
                    if(car.isAvailable()){
                        System.out.println("Car ID : "+car.getCarId()+ " Card brand : "+car.getBrand()+" car model : "+car.getModel());
                    }
                }

                System.out.println("\n Enter the car ID your want to rent");
                String carId = scanner.nextLine();

                System.out.println("\n Enter the number of days for rental : ");
                Integer rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer("CUS"+(customers.size()+1),customerName);
                customers.add(newCustomer);

                Car selectedCar = null;
                for(Car car : cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null){
                    Double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("=== Rental information ===");
                    System.out.println("CustomerId : "+newCustomer.getCustomerId());
                    System.out.println("CustomerName : "+newCustomer.getName());
                    System.out.println("Car "+selectedCar.getBrand()+ " "+selectedCar.getModel());
                    System.out.println("Rental days : "+rentalDays);
                    System.out.printf("Total price: $%.2f%n",totalPrice);

                    System.out.println("\n Confirm Rented (Y/N): ");

                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rendCar(selectedCar,newCustomer,rentalDays);
                        System.out.println("\n Car rent successfully");
                    }else {
                        System.out.println("\n Rent canceled");
                    }
                }else {
                    System.out.println("\n Invalid selection or cur not available for rent");
                }
            } else if (choose == 2) {
                System.out.println("\n Return a car");
                System.out.println("Enter the cur ID your want to return");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars){
                    if(car.getCarId().equals(carId) && !car.isAvailable()){
                        carToReturn = car;
                        break;
                    }
                }
                if(carToReturn != null){
                    Customer customer = null;
                    for (Rental rental : rentals){
                        if(rental.getCar() == carToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer != null){
                        returnCar(carToReturn);
                        System.out.println("Car return successfully by : "+customer.getName());
                    }else {
                        System.out.println("Car was not rent or renter information is missing");
                    }
                }else {
                    System.out.println("Invalid car id or car was not rent");
                }

            }else if (choose == 3){
                break;
            }else {
                System.out.println("Invalid option please inter valid option ");
            }
        }
        System.out.println("\n Thank you for using car rental system");
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        carRentalSystem.addCar(new Car("CAR_01","TOYOTA","T_30",50.0));
        carRentalSystem.addCar(new Car("CAR_02","TOYOTA","T_31",100.0));

        carRentalSystem.menu();
    }
}