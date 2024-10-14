/**
 * Cleaner class extends abstract Hospital employee class.
 */
public class Cleaner extends HospitalEmployee {
    public Cleaner(String empName, int empNum) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Cleaning";
        salary = 23000;
        hours = 45;
    }

    public Cleaner(String empName, int empNum, int salary, int hours) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Cleaning";
        this.salary = salary;
        this.hours = hours;
    }

    public void printSpecialism() {
        System.out.println(specialism);
    }

    @Override
    public String toString() {
        return "Cleaner { \n" +
                "Name: " + empName + '\n' +
                "Employee Number: " + empNum + '\n' +
                "Specialism: " + specialism + '\n' +
                "Salary: " + salary + '\n' +
                "Hours: " + hours + '\n' +
                '}';
    }
}
