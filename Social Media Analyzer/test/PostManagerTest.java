package test.manager;

import model.Post;
import manager.PostManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class PostManagerTest {

    private PostManager postManager;

    @BeforeEach
    void setUp() {
        Map<Integer, Post> initialPosts = new HashMap<>();
        initialPosts.put(1, new Post(1, "Content 1", "Author A", 100, 20, "01/01/2000 00:00"));
        initialPosts.put(2, new Post(2, "Content 2", "Author B", 200, 40, "02/01/2000 01:00"));

        postManager = new PostManager(initialPosts);
    }

    @Test
    void testAddPost() {
        postManager.addPost(new Post(3, "Content 3", "Author C", 300, 60, "03/01/2000 02:00"));

        Post post = postManager.getPostById(3);
        assertNotNull(post);
        assertEquals("Content 3", post.getContent());
    }

    @Test
    void testDeletePostById() {
        boolean isDeleted = postManager.deletePostById(1);
        assertTrue(isDeleted);
        assertNull(postManager.getPostById(1));

        boolean nonExistentPostDeletion = postManager.deletePostById(5);  
        assertFalse(nonExistentPostDeletion);
    }

    @Test
    void testGetPostById() {
        Post post = postManager.getPostById(1);
        assertNotNull(post);
        assertEquals("Content 1", post.getContent());

        assertNull(postManager.getPostById(5)); 
    }

    @Test
    void testGetTopNPostsByLikes() {
        List<Post> topPosts = postManager.getTopNPostsByLikes(1);
        assertNotNull(topPosts);
        assertEquals(1, topPosts.size());
        assertEquals("Content 2", topPosts.get(0).getContent()); 
    }

    @Test
    void testGetTopNPostsByShares() {
        List<Post> topPosts = postManager.getTopNPostsByShares(1);
        assertNotNull(topPosts);
        assertEquals(1, topPosts.size());
        assertEquals("Content 2", topPosts.get(0).getContent()); 
    }
}
