public class Hospital {


    public static void main(String[] args) {
        Doctor doctor1 = new Doctor("Bob", 1234567);
        Doctor doctor2 = new Doctor("Steve", 7654321, 70000, 65);

        Cleaner cleaner1 = new Cleaner("Gerald", 4567845);
        Cleaner cleaner2 = new Cleaner("Tommy", 7654821, 20000, 35);

        Receptionist receptionist1 = new Receptionist("Sarah", 7468243);
        Receptionist receptionist2 = new Receptionist("Joe", 5486321, 18000, 20);

        System.out.println(doctor1);
        doctor1.printSpecialism();
        System.out.println();
        System.out.println(doctor2);

        System.out.println(cleaner1);
        cleaner1.printSpecialism();
        System.out.println();
        System.out.println(cleaner2);

        System.out.println(receptionist1);
        receptionist1.printSpecialism();
        System.out.println();
        System.out.println(receptionist2);
    }
}
