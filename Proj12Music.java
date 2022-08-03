/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package proj9music;

/**
 *
 * @author Allen
 */
import java.util.*;

public class Proj12Music {

//    public static Album[] fillSong(Scanner input){
//    
//}
    /**
     * Searches through musicCollection to find matching song names
     *
     * @param songToFind user inputted song to search for
     * @param musicCollection array of music added
     * @return position in array that song was found
     */
    public static int findSong(String songToFind, Album[] musicCollection) {
        int position = -1;
        for (int i = 0; i < musicCollection.length; i++) {
            if (songToFind.equals(musicCollection[i].getTitle())) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * Prints all albums in a user input year
     *
     * @param input year value to search for
     * @param musicCollection array of songs
     */
    public static void handlePrintYear(Scanner input, Album[] musicCollection) //prints the albums from a specifc year in the collection
    {
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        for (int i = 0; i < musicCollection.length; ++i) {
            if (year == musicCollection[i].getYear()) {
                printSongInfo(i, musicCollection);
            }
        }
    }

    /**
     * Prints all songs of a user input artist
     *
     * @param input user input of artist name
     * @param musicCollection array of music added
     */
    public static void handlePrintArtist(Scanner input, Album[] musicCollection) //prints the albums from a specifc year in the collection
    {
        System.out.println("Please enter an artist: ");
        String artistName = input.nextLine();
        for (int i = 0; i < musicCollection.length; ++i) {
            if (artistName.equals(musicCollection[i].getArtist())) {
                printSongInfo(i, musicCollection);
            }
        }
    }

    /**
     * Adds albums to the array of albums
     *
     * @param input user inputs for title, artist, and year
     * @param musicCollection array to be added to filled with albums
     * @param firstBlank first empty spot in the array
     */
    public static void handleAddAlbum(Scanner input, Album[] musicCollection, int firstBlank) {
        System.out.println("Please enter a title: ");
        String inputTitle = input.nextLine();
        System.out.println("Please enter an artist: ");
        String inputArtist = input.nextLine();
        System.out.println("Please enter a year: ");
        int inputYear = input.nextInt();
        input.nextLine();
        while (inputYear > 2020 || inputYear < 1877) {
            System.out.println("Invalid response, try again.");
            inputYear = input.nextInt();
        }
        musicCollection[firstBlank] = new Album(inputTitle, inputArtist, inputYear);

    }

    /**
     * Changes an album from name to another set of title, artist, and year
     *
     * @param input user inputs for title, artist, and year
     * @param musicCollection array of songs and their information
     */
    public static void handleUpdateAlbum(Scanner input, Album[] musicCollection) {
        System.out.println("Please enter a title: ");
        String toEmpty = input.nextLine();
        System.out.println("Please enter a title: ");
        String toFill = input.nextLine();
        int songLocation = findSong(toEmpty, musicCollection);
        if (songLocation == -1) {
            return;
        }
        System.out.println("Please enter an artist: ");
        String updateArtist = input.nextLine();
        System.out.println("Please enter a year: ");
        int updateYear = input.nextInt();

        musicCollection[songLocation].changeTitle(toFill);
        musicCollection[songLocation].changeArtist(updateArtist);
        musicCollection[songLocation].changeYear(updateYear);
    }

    /**
     * Removes albums of name user inputs and shifts all others forward in the
     * array
     *
     * @param input user input for song to remove
     * @param musicCollection array to be changed
     */
    public static void handleRemoveAlbum(Scanner input, Album[] musicCollection) {
        System.out.println("Please enter a title: ");
        String songTitle = input.nextLine();
        int songPosition = findSong(songTitle, musicCollection);
        if (songPosition == -1) {
            return;
        }
        for (int i = 0; i < musicCollection.length - songPosition - 1; i++) {
            musicCollection[songPosition + i] = musicCollection[songPosition + i + 1];
        }

    }

    /**
     * prints all songs in the collection
     *
     * @param musicCollection collection of songs to be printed
     * @param songsInArray amount of songs user has added
     */
    public static void handlePrintCollection(Album[] musicCollection, int songsInArray) {
        //Album.printAlbum(musicCollection, songsInArray);
        System.out.println(musicCollection);
    }

    /**
     * Prints the formatted information of a specific song
     *
     * @param position location in array to print info of
     * @param musicCollection array to be searched
     */
    public static void printSongInfo(int position, Album[] musicCollection) {
//        System.out.println("Title:  " + musicCollection[position].getTitle());
//        System.out.println("Artist: " + musicCollection[position].getArtist());
//        System.out.println("Year:   " + musicCollection[position].getYear());
System.out.println(musicCollection.toString());

    }

    /**
     * Main method to determine which method will be called
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * initialization and creation of music album
         */
        Scanner input = new Scanner(System.in);
        Album[] musicCollection = new Album[50];
        ArrayList<Album> arrayMusicCollection = new ArrayList<Album>();
        for (int i = 0; i < musicCollection.length; i++) {
            musicCollection[i] = new Album();
        }
        int userInput;
        int songsInArray = 0;
        /**
         * prompt user for input and determine what the user wants
         */
        do {
            System.out.println("Please make a selection:\n"
                    + "1 - Add a new album\n"
                    + "2 - Update an album\n"
                    + "3 - Remove an album\n"
                    + "4 - Print entire collection\n"
                    + "5 - Print by artist\n"
                    + "6 - Print by year\n"
                    + "7 - Quit");
            userInput = input.nextInt();
            if (input.hasNextLine() == true) {
                input.nextLine();
            }
            /**
             * validates user inputs
             */
            while (userInput <= 0 || userInput > 7) {
                System.out.println("Invalid response, try again.");
                userInput = input.nextInt();
                input.nextLine();
            }

            switch (userInput) {
                case 1:
                    handleAddAlbum(input, musicCollection, songsInArray);
                    songsInArray++;
                    break;
                case 2:
                    handleUpdateAlbum(input, musicCollection);
                    break;
                case 3:
                    songsInArray--;
                    if (songsInArray < 0) {
                        return;
                    }
                    handleRemoveAlbum(input, musicCollection);
                    break;
                case 4:
                    handlePrintCollection(musicCollection, songsInArray);
                    break;
                case 5:
                    handlePrintArtist(input, musicCollection);
                    break;
                case 6:
                    handlePrintYear(input, musicCollection);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

            }
        } while (userInput <= 7 && userInput > 0);
    }
}
