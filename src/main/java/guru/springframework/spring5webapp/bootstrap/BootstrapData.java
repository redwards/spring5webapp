package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // =====================================================================
        // Publisher
        //
        Publisher publisher = new Publisher(
                "Bob's Books",
                "123 Main St",
                "Nowheresville",
                "CT",
                "12345-6789"
        );
        publisherRepository.save(publisher);

        // =====================================================================
        // Authors
        //
        Author eric = new Author("Eric", "Idle");
        Author john = new Author("John", "Cleese");
        Author michael = new Author("Michael", "Palin");

        // =====================================================================
        // Book #1
        //
        Book montyPython = new Book("Monty Python Stuff", "12345678901", publisher);

        eric.getBooks().add(montyPython);
        john.getBooks().add(montyPython);
        michael.getBooks().add(montyPython);

        montyPython.getAuthors().add(eric);
        montyPython.getAuthors().add(john);
        montyPython.getAuthors().add(michael);

        authorRepository.save(eric);
        authorRepository.save(john);
        authorRepository.save(michael);
        bookRepository.save(montyPython);

        // =====================================================================
        // Book #2
        //
        Book fawltyTowers = new Book("Fawlty Towers", "10987654321", publisher);
        john.getBooks().add(fawltyTowers);
        fawltyTowers.getAuthors().add(john);

        authorRepository.save(john);
        bookRepository.save(fawltyTowers);

        // =====================================================================
        // Add books to publisher
        //
        publisher.getBooks().add(montyPython);
        publisher.getBooks().add(fawltyTowers);

        publisherRepository.save(publisher);

        // =====================================================================
        // Summary
        //
        System.out.println("================================================================================");
        System.out.println("Started in Boostrap");
        System.out.println("   Number of Authors: " + authorRepository.count());
        System.out.println("     Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("================================================================================");
        System.out.println("           Publisher: " + publisher.toString());
        System.out.println("================================================================================");
    }
}
