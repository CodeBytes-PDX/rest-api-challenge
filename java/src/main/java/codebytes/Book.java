package codebytes;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Book model object with annotations for JSON processing */
public class Book {
    @JsonProperty("id")
    private int id;

    @JsonProperty("author_first_name")
    private String authorFirstName;

    @JsonProperty("author_last_name")
    private String authorLastName;

    @JsonProperty("title")
    private String title;

    @JsonProperty("pages")
    private int pages;


    public Book(int id, String authorFirstName, String authorLastName, String title, int pages) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.pages = pages;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (pages != book.pages) return false;
        if (authorFirstName != null ? !authorFirstName.equals(book.authorFirstName) : book.authorFirstName != null)
            return false;
        if (authorLastName != null ? !authorLastName.equals(book.authorLastName) : book.authorLastName != null)
            return false;
        return title != null ? title.equals(book.title) : book.title == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (authorFirstName != null ? authorFirstName.hashCode() : 0);
        result = 31 * result + (authorLastName != null ? authorLastName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}

