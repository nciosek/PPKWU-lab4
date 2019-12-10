package com.example.ppkwu.model;

public class Employee {

    private String fullName;
    private String organizationUnit;
    private String vcardLink;

    public Employee() {
        this(null, null, null);
    }

    public Employee(String fullName, String organizationUnit, String vcardLink) {
        this.fullName = fullName;
        this.organizationUnit = organizationUnit;
        this.vcardLink = vcardLink;
    }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getOrganizationUnit() { return organizationUnit; }

    public void setOrganizationUnit(String organizationUnit) { this.organizationUnit = organizationUnit; }

    public String getVcardLink() { return vcardLink; }

    public void setVcardLink(String vcardLink) { this.vcardLink = vcardLink; }
}
