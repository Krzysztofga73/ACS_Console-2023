package org.example.Staff;

public class StaffMember {
    protected final Integer id;
    protected final String name;
    protected final String surname;
    protected String department;


    public StaffMember(String name, String surname, Integer id, String department) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.department = department;
    }
    public StaffMember(String[] record) {
        if (record.length != 4) {
            throw new IllegalArgumentException();
        } else {
            try {
                this.id = Integer.parseInt(record[0]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
            this.name = record[1];
            this.surname = record[2];
            this.department = record[3];
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setFunction(String function) {
        this.department = function;
    }

    public String toString(){
        return "Staff member: " + this.name + " " + this.surname + " ID: " + this.id.toString() + " function: " + this.department;
    }
    public String[] toStringRecord(){
        return new String [] {this.id.toString(), this.name, this.surname, this.department};
    }



}
