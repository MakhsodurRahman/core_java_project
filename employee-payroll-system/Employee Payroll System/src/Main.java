import java.util.ArrayList;
import java.util.List;

abstract class Employee{
    private String name;
    private Integer id;

    Employee(String name, Integer id){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public Integer getId(){
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                "salary='" + calculateSalary() + '\'' +
                ", id=" + id +
                '}';
    }
}

class FullTimeEmployee extends Employee{
    private Double monthlySalary;

    FullTimeEmployee(String name,Integer id,Double monthlySalary){
        super(name,id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee{
    private Integer hoursWorked;
    private Double hourlyRate;

    PartTimeEmployee(String name,Integer id,Integer hoursWorked,Double hourlyRate){
        super(name,id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayRollSystem{
    private List<Employee> employeeList;
    public PayRollSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(Integer id){
        Employee employeeToRemove = null;
        for (Employee employee : employeeList){
            if(employee.getId().equals(id)){
                employeeToRemove = employee;
                break;
            }
        }
        if(employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployee(){
        for(Employee employee : employeeList){
            System.out.println("Employee ID : "+employee.getId()+ " Employee Name : "+employee.getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayRollSystem payRollSystem = new PayRollSystem();
        FullTimeEmployee emp1 = new FullTimeEmployee("mak",10000,1002.0);
        FullTimeEmployee emp2 = new FullTimeEmployee("mak2",100001,1002.0);

        payRollSystem.addEmployee(emp1);
        payRollSystem.addEmployee(emp2);
        payRollSystem.displayEmployee();

    }
}