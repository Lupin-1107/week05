package com.tit.day1_csv.advance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EncryptToDecrypt {

    //Secret Key for AES
    private static final String SECRET_KEY = "1234567890123456";

    public static void main(String[] args) {
        //Sample file paths for the input and output
        String csvFile = "employees.csv";
        String csvOutputFile = "employee_data_encrypted.csv";

        //Sample data to write to the CSV file
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John Doe", "Engineering", 75000.00, "john.doe@example.com"),
                new Employee(2, "Jane Smith", "HR", 65000.00, "jane.smith@example.com"),
                new Employee(3, "Anna Johnson", "Marketing", 78000.00, "anna.johnson@example.com")
        );

        //Encrypt and write data to the CSV
        try {
            writeEncryptedCSV(csvOutputFile, employees);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Read and decrypt data from the encrypted CSV
        try {
            List<Employee> decryptedEmployees = readDecryptedCSV(csvOutputFile);
            decryptedEmployees.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AES Encryption method
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return new String(encrypted, StandardCharsets.ISO_8859_1);  // Encoded as ISO_8859_1 to safely store encrypted byte data
    }

    //AES Decryption method
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(encryptedData.getBytes(StandardCharsets.ISO_8859_1));
        return new String(decrypted, StandardCharsets.UTF_8); // Decrypted back to UTF-8
    }

    //Method to write encrypted data into CSV
    public static void writeEncryptedCSV(String filePath, List<Employee> employees) throws Exception {
        FileWriter fileWriter = new FileWriter(filePath);
        try (CSVWriter writer = new CSVWriter(fileWriter)) {
            String[] header = {"Employee ID", "Name", "Department", "Salary", "Email"};
            writer.writeNext(header);

            for (Employee emp : employees) {
                //Encrypt Salary and Email
                String encryptedSalary = encrypt(String.valueOf(emp.getSalary()));
                String encryptedEmail = encrypt(emp.getEmail());

                //Write encrypted data into the CSV file
                String[] employeeData = {
                        String.valueOf(emp.getId()),
                        emp.getName(),
                        emp.getDepartment(),
                        encryptedSalary,
                        encryptedEmail
                };

                writer.writeNext(employeeData);
                System.out.println("Writing encrypted data: " + Arrays.toString(employeeData)); // Debug log
            }

            System.out.println("Encrypted CSV file written successfully: " + filePath);
        } catch (IOException e) {
            throw new Exception("Error writing encrypted data to CSV: " + e.getMessage(), e);
        }
    }

    //Method to read and decrypt data from the CSV file
    public static List<Employee> readDecryptedCSV(String filePath) throws Exception {
        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                String department = nextLine[2];

                //Decrypt Salary and Email
                double salary = Double.parseDouble(decrypt(nextLine[3]));
                String email = decrypt(nextLine[4]);

                //Add decrypted employee data to the list
                employees.add(new Employee(id, name, department, salary, email));
            }

            System.out.println("Decrypted CSV file read successfully.");
        } catch (IOException e) {
            throw new Exception("Error reading or decrypting data from CSV: " + e.getMessage(), e);
        }

        return employees;
    }

    //Employee class to represent employee data
    public static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;
        private String email;

        public Employee(int id, String name, String department, double salary, String email) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', department='" + department + "', salary=" + salary + ", email='" + email + "'}";
        }
    }
}
