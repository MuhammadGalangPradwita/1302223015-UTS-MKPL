/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class TaxFunction {
    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     */
    public static int calculateTax(Employee employee) {
        int monthlySalary = employee.getMonthlySalary();
        int otherMonthlyIncome = employee.getOtherMonthlyIncome();
        int numberOfMonthWorking = employee.getMonthWorkingInYear();
        int deductible = employee.getAnnualDeductible();
        boolean isMarried = employee.isMarried();
        int numberOfChildren = employee.getNumberOfChildren();

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        int nonTaxableIncome = 54000000;

        if (isMarried) {
            nonTaxableIncome += 4500000;
        }

        nonTaxableIncome += numberOfChildren * 4500000;

        int grossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int netIncome = grossIncome - deductible;
        int taxableIncome = netIncome - nonTaxableIncome;

        int tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(tax, 0);
    }
}
