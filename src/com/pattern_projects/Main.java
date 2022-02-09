package com.pattern_projects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input directory to empty:");
        String location = scanner.nextLine();
        var root = Paths.get(location);
        if (Files.exists(root)) {

            if (Files.isDirectory(root)) {
                System.out.println("Delete everything here? (yes, y, no, n)");
                System.out.println(location);


                //Are you sure
                var choice = scanner.next();
                if (choice.toLowerCase().equals("y") || choice.toLowerCase().equals("yes")) {

                    //As Path
                    //fileDeleter(root);

                    //As File
                    fileDeleter(root.toFile());

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
            System.out.println("Deleting: "+file);
            file.delete();
        }
    }

    public static void fileDeleter(Path root) {
        try (var paths = Files.walk(root)) {
            paths.filter(p -> !p.equals(root)).sorted(Comparator.comparingInt(p -> -p.getNameCount())).forEach(path -> {
                try {
                    System.out.println("Deleting: "+path);
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
