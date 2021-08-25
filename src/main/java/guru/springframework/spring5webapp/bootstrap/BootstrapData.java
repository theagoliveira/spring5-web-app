package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Author rod = new Author("Rod", "Johnson");
        authorRepository.save(eric);
        authorRepository.save(rod);

        Book ddd = new Book("Domain Driven Design", "123123");
        Book noEJB = new Book("J2EE Development without EJB", "456789");
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        Publisher testPub = new Publisher("Publisher Name", "Street", "City", "State", "Zip");
        publisherRepository.save(testPub);

        eric.getBooks().add(ddd);
        rod.getBooks().add(noEJB);
        authorRepository.save(eric);
        authorRepository.save(rod);

        testPub.getBooks().add(ddd);
        testPub.getBooks().add(noEJB);
        publisherRepository.save(testPub);

        ddd.getAuthors().add(eric);
        noEJB.getAuthors().add(rod);
        ddd.setPublisher(testPub);
        noEJB.setPublisher(testPub);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("testPub Number of Books: " + testPub.getBooks().size());
    }

}
