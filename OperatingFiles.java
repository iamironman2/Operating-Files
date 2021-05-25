package OperatingFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class OperatingFiles {
    private static final Scanner inputer = new Scanner(System.in);

    public static void creating() {
        try {
            System.out.println("Enter the path where you want to create the text file along with .txt extension");
            String file = inputer.nextLine();
            File creater = new File(file);
            if (!creater.exists()) {
                if (creater.createNewFile()) {
                    System.out.println("File created at the specified path");
                }
            } else {
                System.out.println("File already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void renaming() {
        try {
            System.out.println("Enter the file path you want to rename");
            String ffile = inputer.nextLine();
            Path oldFile = Paths.get(ffile);
            System.out.println("Enter the new File name path");
            String sfile = inputer.nextLine();
            Path newFile = Paths.get(sfile);
            Files.move(oldFile, newFile, StandardCopyOption.REPLACE_EXISTING);
            File newName = new File(sfile);
            if (newName.exists()) {
                System.out.println("Renaming succeeded");
            } else {
                System.out.println("Renaming failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleting() {
        try {
            System.out.println("Enter the file's path you want to delete");
            String file = inputer.nextLine();
            File delete = new File(file);
            if (delete.exists()) {
                if (delete.delete()) {
                    System.out.println("File deleted");
                } else {
                    System.out.println("File not deleted");
                }
            } else {
                System.out.println("File don't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writing() {
        try {
            System.out.println("Enter the file's path where you want to write");
            String input = inputer.nextLine();
            File file = new File(input);
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(input, true);
                System.out.println("Enter what you want to write");
                System.out.println("You can write a single line at once only but many times, press E to exit writing");
                while (true) {
                    String write = inputer.nextLine();
                    if (write.equals("E")) {
                        fileWriter.close();
                        break;
                    }
                    else {
                        fileWriter.write(write + "\n");
                        fileWriter.flush();
                    }
                }
            } else {
                System.out.println("File not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletecon() {
        try {
            System.out.println("Enter the file path where you want to delete contents");
            String path = inputer.nextLine();
            Scanner reader = new Scanner(new File(path));
            List<List<String>> rows = new LinkedList<>();

            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                if (row != null && !row.isBlank()) {
                    List<String> column = new LinkedList<>(Arrays.asList(row.split(" ")));
                    //Take and Check input as it contains
                    String input = inputer.nextLine();
                    List<String> remover = Arrays.asList(input.split(" "));
                    column.removeAll(remover);
                    System.out.println(column);
                    rows.add(column);

                }
            }
            FileWriter fileWriter = new FileWriter("C:\\Java\\Delete it.txt");
            Iterator<List<String>> iterator = rows.iterator();
            while (iterator.hasNext()) {
                List<String> row = iterator.next();
                System.out.println(row);
                fileWriter.write(String.join(" ", row));
                if (iterator.hasNext()) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void replacecon() throws IOException {
        Scanner reader = new Scanner(new File("C:\\Java\\Try replace.txt"));
        List<String> lines = new LinkedList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        System.out.println("Enter the word you want to replace ");
        String frep = inputer.next();
        System.out.println("Enter the replaced word");
        String srep = inputer.next();
        FileWriter fileWriter = new FileWriter("C:\\Java\\Try replace.txt");
        for (String line : lines) {
            if (line.contains(frep)) {
                line = line.replaceAll(frep, srep);
            }
            fileWriter.write(line + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void move() {
        try {
            System.out.println("Note that files cannot be moved in C drive because of Administration permission");
            System.out.println("Enter the file's path you want to move");
            Path fpath = Paths.get(inputer.nextLine());
            System.out.println("Enter the new file path");
            Path spath = Paths.get(inputer.nextLine());
            File file = new File(spath.toString());
            Files.move(fpath, spath);
            if (file.exists()) {
                System.out.println("File moved successfully");
            } else {
                System.out.println("Moving failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void copy() {
        try {
            System.out.println("Note that files cannot be copied in C drive because of administrator permission");
            System.out.println("Enter the file's path which is to be copied");
            File file = new File(inputer.nextLine());
            System.out.println("Enter the copied file path along with name and .txt extension");
            File sfile = new File(inputer.nextLine());
            Path path = Paths.get(file.toString());
            Path spath = Paths.get(sfile.toString());
            Files.copy(path, spath);
            if (sfile.exists()) {
                System.out.println("File copied successfully");
            } else {
                System.out.println("Copying failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner type = new Scanner(System.in);
        do {
            System.out.println("""
                1. Create a text file
                2. Rename a text file
                3. Delete a text file
                4. Write in a text file
                5. Delete in a  text file
                6. Replace in a text file
                7. Move a File
                8. Copy a file
                9. Exit""");
            int choice = type.nextInt();
            switch (choice) {
                case 1 -> creating();
                case 2 -> renaming();
                case 3 -> deleting();
                case 4 -> writing();
                case 5 -> deletecon();
                case 6 -> replacecon();
                case 7 -> move();
                case 8 -> copy();
                case 9 -> System.exit(0);
            }
        } while (true);
    }
}

