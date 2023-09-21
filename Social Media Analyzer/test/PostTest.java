package test.model;

import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post(1, "Hello!", "Om Khokhar", 10, 11, "10/11/2001 10:11");
    }

    @Test
    void testValidDateTimeParsing() {
        assertDoesNotThrow(() -> {
            new Post(1, "content", "author", 10, 11, "10/11/2001 10:11");
        });
    }

    @Test
    void testInvalidDateTimeParsing() {
        assertThrows(DateTimeException.class, () -> {
            new Post(1, "content", "author", 10, 11, "10-11-2001 10:11");
        });
    }

    @Test
    void testToString() {
        String expected = "1 | Hello! | 10 likes | 11 shares | 10/11/2001 10:11";
        assertEquals(expected, post.toString());
    }

}
