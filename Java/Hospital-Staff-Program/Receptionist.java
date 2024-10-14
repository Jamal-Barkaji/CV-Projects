/**
 * Receptionist class extends abstract Hospital employee class.
 */
public class Receptionist extends HospitalEmployee {
    public Receptionist(String empName, int empNum) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Managing appointments";
        salary = 21000;
        hours = 40;
    }

    public Receptionist(String empName, int empNum, int salary, int hours) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Managing appointments";
        this.salary = salary;
        this.hours = hours;
    }

    public void printSpecialism() {
        System.out.println(specialism);
    }

    @Override
    public String toString() {
        return "Receptionist { \n" +
                "Name: " + empName + '\n' +
                "Employee Number: " + empNum + '\n' +
                "Specialism: " + specialism + '\n' +
                "Salary: " + salary + '\n' +
                "Hours: " + hours + '\n' +
                '}';
    }
}
