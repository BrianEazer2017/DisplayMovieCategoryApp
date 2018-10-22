package moviesPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class DisplayMovies {

	public static void main(String[] args) {
	input();
	}

	private static void input() {
		Movie movie1 = new Movie("Borat", "Comedy");
		Movie movie2 = new Movie("Dredd", "Action");
		Movie movie3= new Movie("Mad Max: Fury Road", "Action");
		Movie movie4 = new Movie("They Came Together", "Comedy");
		Movie movie5 = new Movie("One Flew Over the Cuckoo's Nest", "Drama");
		Movie movie6 = new Movie("Hook", "Adventure");
		Movie movie7 = new Movie("Vertigo", "Drama");
		Movie movie8 = new Movie("The Departed", "Drama");
		Movie movie9 = new Movie("The Princess Bride", "Drama");
		Movie movie10 = new Movie("Adaption", "Drama");
		Movie movie11 = new Movie("Drag Me To Hell", "Horror");
		Movie movie12 = new Movie("SpongeBob Squareparnts: The Movie", "Children");
		Movie movie13 = new Movie("The Room", "Drama");
		
		Movie[] moviesArray = {movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11, movie12, movie13};
		List<Movie> moviesList = new ArrayList<>();
		// This for loop adds movies that I thought of.
		for (int i = 0; i < moviesArray.length; i++) {
			moviesList.add(moviesArray[i]);
		}
		// This for loop adds movies from the Grand Circus Github page
		for (int i = 1 ; i < 101; i++) {
			
			moviesList.add(MoviesIO.getMovie(i));
		}
		for (int i = 0; i < moviesList.size(); i++) {
		moviesList.get(i).setCategory((moviesList.get(i).getCategory().substring(0, 1).toUpperCase() + moviesList.get(i).getCategory().substring(1)));
		}
		
		getAllCategories(moviesList);
	}
	
	private static void getAllCategories(List<Movie> moviesList) {
		Scanner sc = new Scanner(System.in);
		TreeSet<String> categories = new TreeSet<>();
		for (int i = 0; i < moviesList.size(); i++) {
			String temp = moviesList.get(i).getCategory();
				categories.add(temp.substring(0,1).toUpperCase() + temp.substring(1) );
		}
		getUserPrompt(sc, moviesList, categories);
	}
		
	private static void getUserPrompt(Scanner sc, List<Movie> moviesList, TreeSet<String> categories) {
		System.out.println("Pick a movie from the following categories:");
		displayCategories(categories);
		if (sc.hasNextInt()) {
			int answer = sc.nextInt();
			if (validateNumber(answer, categories)) {
				displayMovies(sc, moviesList, categories, Integer.toString(answer));
			} 
			else {
				System.out.println("Your number is out of range");
				getUserPrompt(sc, moviesList, categories);
			}
		}
		else if (sc.hasNext()) {
			String answer = sc.next();
			if (validateAnswer(answer, categories)) {
			displayMovies(sc, moviesList, categories, answer);
			} else {
			System.out.println("You failed");
			getUserPrompt(sc, moviesList, categories);
		}
		}
		askToContinue(sc, moviesList, categories);
	}

	private static boolean validateAnswer(String answer, TreeSet<String> categories) {
		for (String cat : categories) {
			if (cat.equals(answer)) {
				return true;
			}
		}
		return false;
	}

	private static boolean validateNumber(int answer, TreeSet<String> categories) {
			if ((answer >= 1) && (answer <= 9)) {
			return true;
			}
		return false;
	}

	private static void askToContinue(Scanner sc, List<Movie> moviesList, TreeSet<String> categories) {
		System.out.println("");
		System.out.println("Would you like to continue?");
		sc.nextLine();
		String answer = sc.nextLine();
		if (answer.matches("[yY][eE]*[sS]*")) {
			getUserPrompt(sc, moviesList, categories);
		}
		else if (answer.matches("[nN][oO]*")){
			System.out.println("It's been real");
		} else {
			System.out.println("Huh?");
			askToContinue(sc, moviesList, categories);
		}	
	}

	
	private static void displayMovies(Scanner sc, List<Movie> moviesList, TreeSet<String> categories, String answer) {
		try {
		if ((Integer.parseInt(answer) >= 1) && (Integer.parseInt(answer) <= 9)) {
			List<String> cats = new ArrayList<String> (categories);
			answer = changeNumberToString(cats, answer);
		} 
		}
		catch(NumberFormatException ex) {
			
		}
		ArrayList<String> moviesInSelectedCategory = new ArrayList<>();
		for (int i = 0; i < moviesList.size(); i++) {
			String temp = moviesList.get(i).getCategory();
				if (temp.matches(answer)) {
					moviesInSelectedCategory.add(moviesList.get(i).getTitle());
				}
			}
		
		Collections.sort(moviesInSelectedCategory); 
		ArrayList<String> copy = moviesInSelectedCategory; 
		ArrayList<String> secondCopy = takeOutThe(copy);
		Collections.sort(secondCopy);
		secondCopy = addTheBack(secondCopy, copy);
		System.out.println("Here are all the movies in the " + answer + " category.");
		for (String movie : secondCopy) {
		System.out.println(movie);
		}
	}

	private static String changeNumberToString(List<String> cats, String answer) {
		String cat = "";
		int index = Integer.parseInt(answer) - 1;
		cat = cats.get(index);
		return cat;
	}

	private static ArrayList<String> addTheBack(ArrayList<String> secondCopy, ArrayList<String> copy) {
		for (int i = 0; i < secondCopy.size(); i++) {
			for (int j = 0; j < secondCopy.size(); j++) {
				if (copy.get(i).substring(4).equals(secondCopy.get(j))){
					secondCopy.set(j, "The " + secondCopy.get(j));
				}
			}
		}
		
		return secondCopy;
	}

	private static ArrayList<String> takeOutThe(ArrayList<String> movies) {
		ArrayList<String> copy = new ArrayList<>();
		for (String movie : movies) {
			if (movie.startsWith("The ")) {
				copy.add(movie.substring(4));
			}
			else {
				copy.add(movie);
			}
		}
		return copy;
	}

	private static void displayCategories(TreeSet<String> categories) {
		int count = 1;
		for (String cat : categories) {
			System.out.println(count + ". " + cat);
			count++;
		}
	}	
	
	
}
