package com.example.hw06;

public class Details {
    private String FirstName;
    private String StudentID;
    private String LastName;
    private String Department;
    private Integer ImageDrawable;

    String getFirstName() {
        return FirstName;
    }

    void setFirstName(String firstName) {
        FirstName = firstName;
    }

    String getStudentID() {
        return StudentID;
    }

    void setStudentID(String studentID) {
        StudentID = studentID;
    }

    String getLastName() {
        return LastName;
    }

    void setLastName(String lastName) {
        LastName = lastName;
    }

    String getDepartment() {
        return Department;
    }

    void setDepartment(String department) {
        Department = department;
    }


    Integer getImageDrawable() {
        return ImageDrawable;
    }

    void setImageDrawable(Integer imageDrawable) {
        ImageDrawable = imageDrawable;
    }

    @Override
    public String toString() {
        return "Details{" +
                "FirstName='" + FirstName + '\'' +
                ", StudentID='" + StudentID + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Department='" + Department + '\'' +
                ", ImageDrawable='" + ImageDrawable + '\'' +
                '}';
    }
}
