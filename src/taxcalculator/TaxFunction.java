/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class TaxFunction {

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

        int tax = (int) Math.round(0.05 * taxableIncome);
        return Math.max(tax, 0);
    }

    // Method untuk validasi bulan kerja (maksimal 12)
    private static int validateMonthWorking(int months) {
        if (months > 12) {
            System.err.println("More than 12 month working per year");
            return 12;
        }
        return months;
    }

    // Method untuk validasi jumlah anak (maksimal 3)
    private static int validateNumberOfChildren(int children) {
        return Math.min(children, 3);
    }

    // Method untuk menghitung penghasilan tidak kena pajak
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = 54000000;
        if (isMarried) {
            nonTaxable += 4500000;
        }
        nonTaxable += numberOfChildren * 4500000;
        return nonTaxable;
    }
}
