/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class TaxFunction {

    // Constants
    private static final Money BASE_NON_TAXABLE_INCOME = new Money(54000000);
    private static final Money MARRIED_ALLOWANCE = new Money(4500000);
    private static final Money CHILD_ALLOWANCE = new Money(4500000);
    private static final double TAX_RATE = 0.05;

    public static int calculateTax(Employee employee) {
        Money monthlySalary = new Money(employee.getMonthlySalary());
        Money otherIncome = new Money(employee.getOtherMonthlyIncome());
        WorkDuration duration = new WorkDuration(employee.getMonthWorkingInYear());
        Money deductible = new Money(employee.getAnnualDeductible());
        MaritalStatus maritalStatus = new MaritalStatus(employee.isMarried());
        Children children = new Children(employee.getNumberOfChildren());

        Money nonTaxableIncome = calculateNonTaxableIncome(maritalStatus, children);
        Money grossIncome = monthlySalary.add(otherIncome).multiply(duration.getMonths());
        Money netIncome = grossIncome.subtract(deductible);
        Money taxableIncome = netIncome.subtract(nonTaxableIncome);

        Money tax = taxableIncome.multiply(TAX_RATE);
        return tax.getAmount();
    }

    private static Money calculateNonTaxableIncome(MaritalStatus status, Children children) {
        Money nonTaxable = BASE_NON_TAXABLE_INCOME;
        if (status.isMarried()) {
            nonTaxable = nonTaxable.add(MARRIED_ALLOWANCE);
        }
        return nonTaxable.add(CHILD_ALLOWANCE.multiply(children.getCount()));
    }
}
