package ru.bookmarks.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bookmarks.domain.Bookmark;
import ru.bookmarks.domain.BookmarkService;
import ru.bookmarks.domain.BookmarksDTO;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
  private final BookmarkService bookmarkService;

  @GetMapping
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page){
    return bookmarkService.getBookmarks(page);
  }

}
