public class student {

    private int id;
    private String name;
    private String regnum;
    private String dept;
    private String address;
    private double mark;

    public student(String name, String regnum, String dept, String address, double mark) {
        this.name = name;
        this.regnum = regnum;
        this.dept = dept;
        this.address = address;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegnum() {
        return regnum;
    }

    public String getDept() {
        return dept;
    }

    public String getAddress() {
        return address;
    }

    public double getMark() {
        return mark;
    }
}
