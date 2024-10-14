/**
 * Doctor class extends abstract Hospital employee class.
 */
public class Doctor extends HospitalEmployee {
    public Doctor(String empName, int empNum) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Treating patients";
        salary = 30000;
        hours = 40;
    }

    public Doctor(String empName, int empNum, int salary, int hours) {
        super(empName, empNum);
        this.empName = empName;
        this.empNum = empNum;
        specialism = "Treating patients";
        this.salary = salary;
        this.hours = hours;
    }

    public void printSpecialism() {
        System.out.println(specialism);
    }

    @Override
    public String toString() {
        return "Doctor { \n" +
                "Name: " + empName + '\n' +
                "Employee Number: " + empNum + '\n' +
                "Specialism: " + specialism + '\n' +
                "Salary: " + salary + '\n' +
                "Hours: " + hours + '\n' +
                '}';
    }
}
