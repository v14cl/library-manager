package com.librarymanager.model;

public class Client {
    private int clientId;
    private String fullName;
    private String phoneNumber;

    public Client() {}

    public Client(int clientId, String fullName, String phoneNumber) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Client(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", fullName='" + fullName + '\'' + ", phoneNumber='" + phoneNumber
                + '\'' + '}';
    }
}