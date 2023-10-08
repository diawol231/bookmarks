package ru.bookmarks.api;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.bookmarks.domain.Bookmark;
import ru.bookmarks.domain.BookmarkRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;
    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();
        bookmarks.add(new Bookmark(null, "Sdsadasd", "asdasdfasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "sua", "dq1231", Instant.now()));
        bookmarks.add(new Bookmark(null, "gafss", "32121", Instant.now()));
        bookmarks.add(new Bookmark(null, "fdasfasf12", "321", Instant.now()));
        bookmarks.add(new Bookmark(null, "134", "2421421312", Instant.now()));
        bookmarks.add(new Bookmark(null, "4121", "das12312", Instant.now()));
        bookmarks.add(new Bookmark(null, "dsa12412", "as1231241", Instant.now()));
        bookmarks.add(new Bookmark(null, "12dsqd1", "asdar41hwesdfasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "dasd1234", "123124rasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "12dsqd1", "asdar41hwesdfasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "15erhgE", "GSJFHADFG", Instant.now()));
        bookmarks.add(new Bookmark(null, "411231254", "asdar41hwesdfasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "12dsqd1", "SGDSFSF", Instant.now()));
        bookmarks.add(new Bookmark(null, "SGDSDFS", "asdar41hwesdfasd", Instant.now()));
        bookmarks.add(new Bookmark(null, "DSF23T23", "24365242R", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1,10,2,1,true,false,true,false",
            "2,5,2,2,false,true,false,true"
    })
    public void shouldGetBookmarks(int pageNo, int totalElements, int totalPages,
                                   int currentPage, boolean isFirst, boolean isLast,
                                   boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc.perform(get("/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));
    }

}
