package ru.mirea.sladkinaaa.employeedb;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String power;
    public int powerLevel;

    public Employee(String name, String power, int powerLevel) {
        this.name = name;
        this.power = power;
        this.powerLevel = powerLevel;
    }
}
