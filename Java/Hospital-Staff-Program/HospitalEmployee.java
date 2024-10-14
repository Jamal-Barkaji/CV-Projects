/**
 * Abstract class for holding employee information.
 */
public abstract class HospitalEmployee {
    /**
     * Name of employee.
     */
    protected String empName;
    /**
     * Unique employee number.
     */
    protected int empNum;
    /**
     * Employee's specialism.
     */
    protected String specialism;
    /**
     * Employee's salary.
     */
    protected int salary;
    /**
     * Employee's work hours.
     */
    protected int hours;

    /////////////////////////////////////////////////////////////////////

    /**
     * Constructor.
     * @param empName name of employee.
     * @param empNum unique employee number.
     */
    public HospitalEmployee(String empName, int empNum) {

    }

    /**
     * Prints specialism of employee.
     */
    public abstract void printSpecialism();
}
