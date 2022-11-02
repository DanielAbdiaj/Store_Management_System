package Module;

import java.io.Serializable;


public class Manager extends User implements Serializable {



    String name;
    String surname;
    String phoneNumber;
    Date birthday;
    String email;
    double salary;
    String idCardNumber;

    double salaryOverAPeriod;

    public Manager()
    {
        super();
        birthday = new Date();
        salaryOverAPeriod = 0;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getIdCardNumber() { return idCardNumber; }
    public void setIdCardNumber(String idCardNumber) { this.idCardNumber = idCardNumber; }

    public double getSalaryOverAPeriod() { return salaryOverAPeriod; }

    public void setSalaryOverAPeriod(int monthFrom, int yearFrom, int monthTo, int yearTo)
    {
        int yearsBetween = yearTo - yearFrom;
        int monthsBetween = monthTo - monthFrom;
        int nrOfMonthsBetween = (yearsBetween * 12) + monthsBetween;

        salaryOverAPeriod = salary * nrOfMonthsBetween;
    }

    public void resetSalaryOverAPeriod() { salaryOverAPeriod = 0; }

}
