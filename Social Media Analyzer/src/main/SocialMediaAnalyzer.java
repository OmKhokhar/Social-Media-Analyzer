package main;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Post;
import manager.PostManager;
import exceptions.InvalidMenuOption;
import exceptions.InvalidPostData;

public class SocialMediaAnalyzer {

    private PostManager postManager;
    private Scanner scanner;

    public SocialMediaAnalyzer() {
        postManager = new PostManager();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SocialMediaAnalyzer analyzer = new SocialMediaAnalyzer();
        analyzer.initializeApp();
        analyzer.start();
    }

    private void initializeApp() {
        System.out.println("Hello! Welcome to the Social Media Analyzer");
    }

    public void start() {
        boolean running = true;

        while (running) {
            try {
                displayMenu();

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addPost();
                        break;
                    case 2:
                        deletePost();
                        break;
                    case 3:
                        retrievePost();
                        break;
                    case 4:
                        displayTopNPostsByLikes();
                        break;
                    case 5:
                        displayTopNPostsByShares();
                        break;
                    case 6:
                        running = false; 
                        System.out.println("Thank you. See you again !");
                        break;
                    default:
                        throw new InvalidMenuOption("Sorry, this menu option is Invalid");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); 
            } catch (InvalidMenuOption e) {
                System.out.println(e.getMessage());
            } catch (InvalidPostData e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Sorry ! We encountered an unexpected error: " + e.getMessage());
            }
        }
    }

    private void addPost() throws InvalidPostData {
        System.out.print("Enter the post ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter the post content: ");
        String content = scanner.nextLine();

        if (content.contains(",")) {
            throw new InvalidPostData("Commas should not be included");
        }

        System.out.print("Enter the author of the post: ");
        String author = scanner.nextLine();

        System.out.print("Enter of likes of the post: ");
        int likes = scanner.nextInt();

        System.out.print("Enter the number of shares of the post: ");
        int shares = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter the date and time of the post (Use format DD/MM/YYYY HH:MM:)");
        String dateTime = scanner.nextLine();

        Post post = new Post(id, content, author, likes, shares, dateTime);
        postManager.addPost(post);

        System.out.println("You have successfully added the post to the collection!");
    }

    private void deletePost() {
        System.out.print("Enter the post ID to delete: ");
        int id = scanner.nextInt();
        if (postManager.deletePostById(id)) {
            System.out.println("You have successfully deleted the Post!");
        } else {
            System.out.println("Sorry, we cannot find this post!");
        }
    }

    private void retrievePost() {
        System.out.print("Enter the post ID: ");
        int id = scanner.nextInt();
        Post post = postManager.getPostById(id);
        if (post != null) {
            System.out.println(post);
        } else {
            System.out.println("Sorry, we cannot find this post!");
        }
    }

    private void displayTopNPostsByLikes() {
        System.out.print("Enter the number of posts to be retrieved: ");
        int n = scanner.nextInt();
        List<Post> topPosts = postManager.getTopNPostsByLikes(n);
        if (topPosts.size() == 0) {
            System.out.println("Sorry! But we have no posts available");
            return;
        }
        System.out.println("The top-" + topPosts.size() + " posts having most likes are:");
        for (int i = 0; i < topPosts.size(); i++) {
            System.out.println((i + 1) + ") " + topPosts.get(i));
        }
    }

    private void displayTopNPostsByShares() {
        System.out.print("Enter the number of posts to be retrieved: ");
        int n = scanner.nextInt();
        List<Post> topPosts = postManager.getTopNPostsByShares(n);
        if (topPosts.size() == 0) {
            System.out.println("Sorry! But we have no posts available.");
            return;
        }
        System.out.println("The top-" + topPosts.size() + " posts having most shares are:");
        for (int i = 0; i < topPosts.size(); i++) {
            System.out.println((i + 1) + ") " + topPosts.get(i));
        }
    }

    private void displayMenu() {
        System.out.println("\nHello! Welcome to the Social Media Analyzer");
        System.out.println("-----------------------------------------------");
        System.out.println("> Select options from the main menu");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Add a post");
        System.out.println("2. Delete an existing post");
        System.out.println("3. Retrieve a post");
        System.out.println("4. Retrieve the top N posts by number of likes");
        System.out.println("5. Retrieve the top N posts by number of shares");
        System.out.println("6. Exit");
        System.out.print("Please select: ");
    }
}
