/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class TaxFunction {

    // Constants to replace magic numbers
    private static final int BASE_NON_TAXABLE_INCOME = 54000000;
    private static final int MARRIED_ALLOWANCE = 4500000;
    private static final int CHILD_ALLOWANCE = 4500000;
    private static final int MAX_CHILDREN = 3;
    private static final int MAX_MONTHS_IN_YEAR = 12;
    private static final double TAX_RATE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     */
    public static int calculateTax(Employee employee) {
        int monthlySalary = employee.getMonthlySalary();
        int otherMonthlyIncome = employee.getOtherMonthlyIncome();
        int numberOfMonthWorking = validateMonthWorking(employee.getMonthWorkingInYear());
        int deductible = employee.getAnnualDeductible();
        int numberOfChildren = validateNumberOfChildren(employee.getNumberOfChildren());
        boolean isMarried = employee.isMarried();

        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int grossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int netIncome = grossIncome - deductible;
        int taxableIncome = netIncome - nonTaxableIncome;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);
        return Math.max(tax, 0);
    }

    // Method untuk validasi bulan kerja (maksimal 12)
    private static int validateMonthWorking(int months) {
        if (months > MAX_MONTHS_IN_YEAR) {
            System.err.println("More than 12 month working per year");
            return MAX_MONTHS_IN_YEAR;
        }
        return months;
    }

    // Method untuk validasi jumlah anak (maksimal 3)
    private static int validateNumberOfChildren(int children) {
        return Math.min(children, MAX_CHILDREN);
    }

    // Method untuk menghitung penghasilan tidak kena pajak
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = BASE_NON_TAXABLE_INCOME;
        if (isMarried) {
            nonTaxable += MARRIED_ALLOWANCE;
        }
        nonTaxable += numberOfChildren * CHILD_ALLOWANCE;
        return nonTaxable;
    }
}
