/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import static taxcalculator.Salary.*;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    private Employee(Builder builder) {
        this.employeeId = builder.employeeId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.idNumber = builder.idNumber;
        this.address = builder.address;
        this.yearJoined = builder.yearJoined;
        this.monthJoined = builder.monthJoined;
        this.dayJoined = builder.dayJoined;
        this.isForeigner = builder.isForeigner;
        this.gender = builder.gender;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public static class Builder {
        private String employeeId;
        private String firstName;
        private String lastName;
        private String idNumber;
        private String address;

        private int yearJoined;
        private int monthJoined;
        private int dayJoined;
        private boolean isForeigner;
        private boolean gender;

        public Builder setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setYearJoined(int yearJoined) {
            this.yearJoined = yearJoined;
            return this;
        }

        public Builder setMonthJoined(int monthJoined) {
            this.monthJoined = monthJoined;
            return this;
        }

        public Builder setDayJoined(int dayJoined) {
            this.dayJoined = dayJoined;
            return this;
        }

        public Builder setIsForeigner(boolean isForeigner) {
            this.isForeigner = isForeigner;
            return this;
        }

        public Builder setGender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1:
                monthlySalary = gradeA;
                break;
            case 2:
                monthlySalary = gradeB;
                break;
            case 3:
                monthlySalary = gradeC;
                break;
            default:
                monthlySalary = 0;
        }

        if (isForeigner) {
            monthlySalary *= foreignerMultiplier;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();

        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear,
                annualDeductible, spouseIdNumber == null || spouseIdNumber.isEmpty(), childIdNumbers.size());
    }
}
