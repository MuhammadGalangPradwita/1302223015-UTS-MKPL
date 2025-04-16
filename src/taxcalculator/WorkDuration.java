/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class WorkDuration {
    private static final int MAX_MONTHS = 12;
    private final int months;

    public WorkDuration(int months) {
        if (months > MAX_MONTHS) {
            System.err.println("More than 12 month working per year");
            this.months = MAX_MONTHS;
        } else {
            this.months = months;
        }
    }

    public int getMonths() {
        return months;
    }
}

