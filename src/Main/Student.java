package Main;

public class Student {
    private String barcode;
    private String name;
    private double gpa;

    public Student(String barcode, String name, double gpa) {
        this.barcode = barcode;
        this.name = name;
        this.gpa = gpa;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
