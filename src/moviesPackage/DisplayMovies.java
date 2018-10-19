package moviesPackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DisplayMovies {

	public static void main(String[] args) {
	input();
	}

	private static void input() {
		Movie movie1 = new Movie("Borat", "Comedy");
		Movie movie2 = new Movie("Dredd", "Action");
		movie2.setCategory("Scifi");
		Movie movie3= new Movie("Mad Max: Fury Road", "Action");
		Movie movie4 = new Movie("They Came Together", "Comedy");
		Movie movie5 = new Movie("One Flew Over the Cuckoo's Nest", "Drama");
		Movie movie6 = new Movie("Hook", "Adventure");
		movie6.setCategory("Drama");
		Movie movie7 = new Movie("Vertigo", "Drama");
		movie7.setCategory("Suspense");
		Movie movie8 = new Movie("The Departed", "Drama");
		Movie movie9 = new Movie("The Princess Bride", "Drama");
		movie9.setCategory("Comedy");
		Movie movie10 = new Movie("Adaption", "Drama");
		Movie movie11 = new Movie("Drag Me To Hell", "Horror");
		movie11.setCategory("Comedy");
		Movie movie12 = new Movie("SpongeBob Squareparnts: The Movie", "Children");
		movie12.setCategory("Comedy");
		movie12.setCategory("Animated");
		Movie[] moviesArray = {movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11, movie12, };
		List<Movie> moviesList = new ArrayList<>();
		for (int i = 0; i < moviesArray.length; i++) {
			moviesList.add(moviesArray[i]);
		}
		getCategories(moviesList);
	}

	private static void getCategories(List<Movie> moviesList) {
		Scanner sc = new Scanner(System.in);
		HashSet<String> categories = new HashSet<>();
		for (int i = 0; i < moviesList.size(); i++) {
			List<String> temp = moviesList.get(i).getCategory();
			for (int j = 0; j < temp.size(); j++) {
				categories.add(temp.get(j));
			}
		}
		getUserPrompt(sc, moviesList, categories);
	}
		
	private static void getUserPrompt(Scanner sc, List<Movie> moviesList, HashSet<String> categories) {
		System.out.println("Pick a movie from the following categories:");
		displayCategories(categories);
		String answer = sc.nextLine();
		displayMovies(sc, moviesList, categories, answer);
		askToContinue(sc, moviesList, categories);
	}

	private static void askToContinue(Scanner sc, List<Movie> moviesList, HashSet<String> categories) {
		System.out.println("");
		System.out.println("Would you like to continue?");
		String answer = sc.nextLine();
		if (answer.matches("[yY][eE]*[sS]*")) {
			getUserPrompt(sc, moviesList, categories);
		}
		
	}

	private static void displayMovies(Scanner sc, List<Movie> moviesList, HashSet<String> categories, String answer) {
		HashSet<String> moviesInSelectedCategory = new HashSet<>();
		for (int i = 0; i < moviesList.size(); i++) {
			List<String> temp = moviesList.get(i).getCategory();
			for (int j = 0; j < temp.size(); j++) {
				if (temp.get(j).matches(answer)) {
					moviesInSelectedCategory.add(moviesList.get(i).getTitle());
				}
			}
		}
		System.out.println("Here are all the movies in the " + answer + " category.");
		for (String movie : moviesInSelectedCategory) {
		System.out.println(movie);
		}
	}

	private static void displayCategories(HashSet<String> categories) {
		for (String cat : categories) {
			System.out.println(cat);
		}
	}	
	
}
