import com.mjc.school.service.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ServiceLayerTest {

    private NewsDtoStorage newsDtoStorage;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        newsDtoStorage = new NewsDtoStorage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testGetAllNewsDto_NotEmpty() {
        newsDtoStorage.printAllNewsDto();
        String output = outContent.toString();
        assertFalse(output.isBlank(), "getAllNewsDto should print newsDto objects");
    }

    @Test
    void testGetNewsDtoById_ExistingAndNotExisting() {
        NewsDto first = newsDtoStorage.getNewsDtoById(1L);
        if (first != null) {
            assertEquals(1L, first.getId());
        }
        assertNull(newsDtoStorage.getNewsDtoById(999L));
    }

    @Test
    void testCreateNewsDto_AddsAndPrints() {
        NewsDto newNews = new NewsDto("Test Title", "Test Content", 1L);
        int beforeSize = newsDtoStorage.getAllNewsDto().size();
        newsDtoStorage.createNewsDto(newNews);
        int afterSize = newsDtoStorage.getAllNewsDto().size();

        assertEquals(beforeSize + 1, afterSize);
        assertTrue(outContent.toString().contains("Test Title"));
    }

    @Test
    void testUpdateNewsDto_UpdatesExisting() {
        NewsDto existing = newsDtoStorage.getAllNewsDto().get(0);
        Long id = existing.getId();

        NewsDto updatedDto = new NewsDto("Updated Title", "Updated Content", existing.getAuthorId());
        updatedDto.setId(id);
        newsDtoStorage.updateNewsDto(updatedDto);
        NewsDto stored = newsDtoStorage.getNewsDtoById(id);
        assertEquals("Updated Title", stored.getTitle());
        assertEquals("Updated Content", stored.getContent());
        assertTrue(outContent.toString().contains("Updated Title"));
    }

    @Test
    void testDeleteNewsDtoById_Removes() {
        NewsDto existing = newsDtoStorage.getAllNewsDto().get(0);
        Long id = existing.getId();
        newsDtoStorage.deleteNewsDtoById(id);
        assertFalse(newsDtoStorage.isNewsIdExist(id));
    }
}
