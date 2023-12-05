package Model.Worker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public abstract class Worker implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Worker() {
        this.pesel = String.valueOf(0);
        this.name = String.valueOf(0);
        this.surname = String.valueOf(0);
        this.position = String.valueOf(0);
        this.salary = (double) 0;
        this.phoneNumber = 0;
    }

    private  String pesel;
    private  String name;
    private   String surname;
    private  String position;
    private  Double salary;
    private  Integer phoneNumber;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = Double.parseDouble(salary);
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Integer.parseInt(phoneNumber);
    }

    public ArrayList<String> getBasicFieldName() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Pesel");
        list.add("Name");
        list.add("Surname");
        list.add("Position");
        list.add("Salary");
        list.add("PhoneNumber");
        return list;
    }


}









