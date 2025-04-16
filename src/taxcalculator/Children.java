/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

public class Children {
    private static final int MAX_CHILDREN = 3;
    private final int count;

    public Children(int count) {
        this.count = Math.min(count, MAX_CHILDREN);
    }

    public int getCount() {
        return count;
    }
}

