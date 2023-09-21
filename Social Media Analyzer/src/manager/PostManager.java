package manager;

import model.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PostManager {

    private Map<Integer, Post> postsMap;
    private static final String FILE_PATH = "posts.csv";

    public PostManager() {
        postsMap = new HashMap<>();
        try {
            loadPostsFromCSV(FILE_PATH);
        } catch (IOException e) {
            System.err.println("Sorry! We cannot load posts from CSV: " + e.getMessage());
        }
    }

    public PostManager(Map<Integer, Post> mockPosts) {
        this.postsMap = new HashMap<>(mockPosts);
    }

    private void loadPostsFromCSV(String filePath) throws IOException {
        this.postsMap = Files.lines(Paths.get(filePath))
                .skip(1)
                .map(line -> {
                    String[] details = line.split(",");
                    int id = Integer.parseInt(details[0].trim());
                    String content = details[1].trim();
                    String author = details[2].trim();
                    int likes = Integer.parseInt(details[3].trim());
                    int shares = Integer.parseInt(details[4].trim());
                    String dateTime = details[5].trim();
                    return new Post(id, content, author, likes, shares, dateTime);
                })
                .collect(Collectors.toMap(Post::getId, post -> post));
    }


    public void savePostsToCSV(String filePath) {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("ID,content,author,likes,shares,date-time");
            for (Post post : postsMap.values()) {
                lines.add(post.getId() + "," + post.getContent() + "," + post.getAuthor() + "," + post.getLikes() + "," + post.getShares() + "," + post.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPost(Post post) {
        postsMap.put(post.getId(), post);
        savePostsToCSV(FILE_PATH);
    }

    public boolean deletePostById(int id) {
        boolean removed = (postsMap.remove(id) != null);
        if (removed) {
            savePostsToCSV(FILE_PATH);
        }
        return removed;
    }

    public Post getPostById(int id) {
        return postsMap.get(id);
    }

    public List<Post> getTopNPostsByLikes(int n) {
        return postsMap.values().stream()
                .sorted(Comparator.comparingInt(Post::getLikes).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Post> getTopNPostsByShares(int n) {
        return postsMap.values().stream()
                .sorted(Comparator.comparingInt(Post::getShares).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}
