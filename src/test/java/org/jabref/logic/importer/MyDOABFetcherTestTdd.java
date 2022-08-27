package org.jabref.logic.importer;

import java.util.List;
import java.util.stream.Stream;

import org.jabref.logic.importer.fetcher.DOABFetcherTDD;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.StandardEntryType;
import org.jabref.testutils.category.FetcherTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FetcherTest
@SuppressWarnings("checkstyle:RegexpMultiline")
public class MyDOABFetcherTestTdd {
    private DOABFetcherTDD fetcher = new DOABFetcherTDD();

    @Test
    public void getNameTest() {
        assertEquals("DOAB", fetcher.getName());
    }

   @SuppressWarnings("checkstyle:WhitespaceAround")
    public static Stream<Arguments> testPerformSearch() {
        return Stream.of(Arguments.of(new BibEntry(StandardEntryType.Book)
                                .withField(StandardField.AUTHOR, "David Pol")
                                .withField(StandardField.TITLE, "I Open Fire")
                                .withField(StandardField.DOI, "10.21983/P3.0086.1.00")
                                .withField(StandardField.PAGES, "56")
                                .withField(StandardField.YEAR, "2014")
                                .withField(StandardField.URL, "http://library.oapen.org/handle/20.500.12657/25535")
                                .withField(StandardField.URI, "https://directory.doabooks.org/handle/20.500.12854/34739")
                                .withField(StandardField.LANGUAGE, "English")
                                .withField(StandardField.KEYWORDS, "poetry, love, warfare")
                                .withField(StandardField.PUBLISHER, "punctum books"),
                        "i open fire"
                ),
                Arguments.of(new BibEntry(StandardEntryType.Book)
                                .withField(StandardField.AUTHOR, "Ronald Snijder")
                                .withField(StandardField.TITLE, "The deliverance of open access books")
                                .withField(StandardField.SUBTITLE, "Examining usage and dissemination")
                                .withField(StandardField.DOI, "10.26530/OAPEN_1004809")
                                .withField(StandardField.PAGES, "234")
                                .withField(StandardField.YEAR, "2019")
                                .withField(StandardField.URL, "http://library.oapen.org/handle/20.500.12657/25287")
                                .withField(StandardField.URI, "https://directory.doabooks.org/handle/20.500.12854/26303")
                                .withField(StandardField.LANGUAGE, "English")
                                .withField(StandardField.KEYWORDS, "Open Access, Monographs, OAPEN Library, " +
                                        "Directory of Open Access Books")
                                .withField(StandardField.PUBLISHER, "Amsterdam University Press"),
                        "the deliverance of open access books"
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testPerformSearch(BibEntry expected, String query) throws FetcherException {
        List<BibEntry> entries = fetcher.performSearch(query);

        entries.stream().forEach(entry -> entry.clearField(StandardField.ABSTRACT));
        assertEquals(List.of(expected), entries);
    }
}
