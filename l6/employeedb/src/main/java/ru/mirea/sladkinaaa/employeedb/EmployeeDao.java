package ru.mirea.sladkinaaa.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    List<Employee> getAll();

    @Query("SELECT * FROM Employee WHERE id = :id")
    Employee getById(int id);

    @Insert
    void insert(Employee employee);

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insertAll(Employee... employees);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);
}
