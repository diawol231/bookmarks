package ru.bookmarks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class BookmarksDTO {
  private List<BookmarkDTO> data;
  private int totalElements;
  private int totalPages;
  private int currentPage;
  @JsonProperty("isFirst")
  private boolean isFirst;
  @JsonProperty("isLast")
  private boolean isLast;
  private boolean hasNext;
  private boolean hasPrevious;

  public BookmarksDTO(Page<BookmarkDTO> bookmarksPage){
    this.setData(bookmarksPage.getContent());
    this.setTotalElements(bookmarksPage.getNumberOfElements());
    this.setTotalPages(bookmarksPage.getTotalPages());
    this.setCurrentPage(bookmarksPage.getNumber() + 1);
    this.setFirst(bookmarksPage.isFirst());
    this.setLast(bookmarksPage.isLast());
    this.setHasNext(bookmarksPage.hasNext());
    this.setHasPrevious(bookmarksPage.hasPrevious());
  }
}
