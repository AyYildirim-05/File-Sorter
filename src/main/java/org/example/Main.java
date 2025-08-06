package org.example;

import java.io.File;

public class Main {
    static String prefix;

    public static void main(String[] args) {
        prefix = getPath() + File.separator;

        File[] list = folderContents(getPath());

        sortingPath(list);
    }

    /**
     * Method that sorts the files within the downloads folder based on their extension types.
     * @param files list that contains all the contents of the downloads folder
     */
    public static void sortingPath(File[] files) {
        for (File file : files) {
            if (file.isFile()) { // check if it is a file
                String fileName = file.getName(); // get the name of the file

                int lastDot = fileName.lastIndexOf(".");
                if (lastDot != -1) {
                    String extension = fileName.substring(lastDot + 1); // get the file extension

                    // create folder based on an extension
                    File extFolder = new File(getPath() + File.separator + extension);
                    if (!extFolder.exists()) {
                        extFolder.mkdirs();
                    }

                    // move the file into new directory
                    File newFile = new File(extFolder.getAbsolutePath() + File.separator + fileName);
                    boolean success = file.renameTo(newFile);
                    if (success) {
                        System.out.println("Moved " + fileName + " to " + extFolder.getAbsolutePath());
                    } else {
                        System.out.println("Failed to move " + fileName);
                    }
                } else {
                    System.out.println("No extension found for file: " + fileName);
                }
            }
        }
    }

    /**
     * Method that gets the path of the downloads folder
     * @return the path of the downloads folder
     */
    public static String getPath() {
        String home = System.getProperty("user.home");
        return home + File.separator + "Downloads";
    }

    /**
     * Method that gets the files within a given path
     * @param folderPath the given path to sort
     * @return all the paths of the files
     */
    public static File[] folderContents(String folderPath) {
        File folder = new File(folderPath);
        return folder.listFiles();
    }

}
