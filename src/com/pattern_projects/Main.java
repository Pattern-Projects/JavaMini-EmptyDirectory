package com.pattern_projects;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input directory to empty:");
        String location = scanner.nextLine();
        var root = new File(location);
        if (root.exists()) {

            if (root.isDirectory()) {
                System.out.println("Delete everything here? (yes, y, no, n)");
                System.out.println(location);


                //Are you sure
                var choice = scanner.next();
                if (choice.toLowerCase().equals("y") || choice.toLowerCase().equals("yes")) {

                    fileDeleter(root);
                    System.out.println("Complete");
                } else {
                    //Does not want to delete
                    System.out.println("Will not delete directory contents.");
                    System.out.println("Process aborted.");
                }


            } else {
                //Is not a directory
                System.out.println("This is a file, not a directory.");
                System.out.println("Process aborted.");
            }
        } else {
            //Does not exist
            System.out.println("This directory does not exist.");
            System.out.println("Process aborted.");

        }
    }


    public static void fileDeleter(File directory) {
        var list = directory.listFiles();
        for (var file : list) {
            if (file.isDirectory()) {
                fileDeleter(file);
            }
            file.delete();
        }
    }

}
