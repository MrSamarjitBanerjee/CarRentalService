import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
	
	  private String carId;
	  private String Brand;
	  private String Model;
	  private double BasePricePerDay;
	  private boolean isavailable;
	  
	  
	  
	  
	public Car(String carId, String brand, String model, double basePricePerDay) {
		super();
		this.carId = carId;
		Brand = brand;
		Model = model;
		BasePricePerDay = basePricePerDay;
		this.isavailable = true;
		}
	public String getCarId() {
		return carId;
	}
	
	public String getBrand() {
		return Brand;
	}
	
	public String getModel() {
		return Model;
	}
	
	public double getBasePricePerDay() {
		return BasePricePerDay;
	}
	
	public boolean isIsavailable() {
		return isavailable;
	}
	public void rent() {
	  isavailable = false;
	}
	public void returncar() {
		isavailable = true;
	}
	
	public double CalculatePrice(int RentalDays) {
		return BasePricePerDay*RentalDays;
		
		
	}
	
	
}
class Customer{
         private String custname;
         private String custId;
         
         public Customer(String custname, String custId) {
             this.custname = custname;
             this.custId = custId;
         }

         public String getcustname() {
             return custname;
         }

         public String getcustId() {
             return custId;
         }
}



class Rental{
	private Car car;
	private Customer customer;
	private int days;
	public Rental(Car car, Customer customer, int days) {
		super();
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
	public int getdays() {
		return days;
	}
	
	
	
	
}


class CarRentalSystem {
	
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	public CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();
		
	}
	
	
   public void AddCar(Car car) {
	   cars.add(car);
	}
	
   public void AddCustomer(Customer customer) {
	   customers.add(customer);
	}
   public void RentCar(Car car, Customer customer,int days) {
	   if(car.isIsavailable()) {
		   car.rent();
		   rentals.add(new Rental(car,customer,days));
		   
	   }
	   else {
		   System.out.println("Car not available for rent!!");
	   }
   }
   
   
	
   public void returnCar(Car car) {
	  
	   Rental rentaltoremove = null;
	   for(Rental rental : rentals ) {
		   if(rental.getCar() == car) {
			   rentaltoremove = rental;
			   break;
		   }
	   }
		   if(rentaltoremove != null) {
			   rentals.remove(rentaltoremove);
			   System.out.println("Car returned Successfully");
		   }
		   else {
			   System.out.println("Car not Rented");
		   }
		   car.returncar();
	   
   }
  
	


public void menu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("===== Car Rental System =====");
        System.out.println("1. Rent a Car");
        System.out.println("2. Return a Car");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.println("\n== Rent a Car ==\n");
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            System.out.println("\nAvailable Cars:");
            for (Car car : cars) {
                if (car.isIsavailable()) {
                	
               System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                }
            }

            System.out.print("\nEnter the car ID you want to rent: ");
            String carId = scanner.nextLine();

            System.out.print("Enter the number of days for rental: ");
            int rentalDays = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
            AddCustomer(newCustomer);

            Car selectedCar = null;
            for (Car car : cars) {
                if (car.getCarId().equals(carId) && car.isIsavailable()) {
                    selectedCar = car;
                    break;
                }
            }

            if (selectedCar != null) {
                double totalPrice = selectedCar.CalculatePrice(rentalDays);
                System.out.println("\n== Rental Information ==\n");
                System.out.println("Customer ID: " + newCustomer.getcustId());
                System.out.println("Customer Name: " + newCustomer.getcustname());
                System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                System.out.println("Rental Days: " + rentalDays);
                System.out.printf("Total Price: /-%.2f%n", totalPrice);

                System.out.print("\nConfirm rental (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {
                    RentCar(selectedCar, newCustomer, rentalDays);
                    System.out.println("\nCar rented successfully.");
                } else {
                    System.out.println("\nRental canceled.");
                }
            } else {
                System.out.println("\nInvalid car selection or car not available for rent.");
            }
        } else if (choice == 2) {
            System.out.println("\n== Return a Car ==\n");
            System.out.print("Enter the car ID you want to return: ");
            String carId = scanner.nextLine();

            Car carToReturn = null;
            for (Car car : cars) {
                if (car.getCarId().equals(carId) && !car.isIsavailable()) {
                    carToReturn = car;
                    break;
                }
            }

            if (carToReturn != null) {
                Customer customer = null;
                for (Rental rental : rentals) {
                    if (rental.getCar() == carToReturn) {
                        customer = rental.getCustomer();
                        break;
                    }
                }

                if (customer != null) {
                    returnCar(carToReturn);
                    System.out.println("Car returned successfully by " + customer.getcustname());
                } else {
                    System.out.println("Car was not rented or rental information is missing.");
                }
            } else {
                System.out.println("Invalid car ID or car is not rented.");
            }
        } else if (choice == 3) {
            break;
        } else {
            System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    System.out.println("\nThank you for using the Car Rental System!");
}

}




public class Main {

	public static void main(String[] args) {
		  CarRentalSystem rentalSystem = new CarRentalSystem();

	        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
	        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
	        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
	        rentalSystem.AddCar(car1);
	        rentalSystem.AddCar(car2);
	        rentalSystem.AddCar(car3);

	        rentalSystem.menu();
	}

}
