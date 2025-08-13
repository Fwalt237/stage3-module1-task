package com.mjc.school;

import com.mjc.school.controller.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller userInterface = new Controller(scanner);

        userInterface.start();

    }
}