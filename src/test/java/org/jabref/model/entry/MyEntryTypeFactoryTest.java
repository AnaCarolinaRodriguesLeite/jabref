package org.jabref.model.entry;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.jabref.model.entry.field.BibField;
import org.jabref.model.entry.field.FieldPriority;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.EntryType;
import org.jabref.model.entry.types.EntryTypeFactory;
import org.jabref.model.entry.types.IEEETranEntryType;
import org.jabref.model.entry.types.UnknownEntryType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyEntryTypeFactoryTest {
    private static final EntryType CUSTOM_TYPE = new UnknownEntryType("customType");

    private EntryTypeFactory entryType;
    private BibEntryType entry;

    @BeforeEach
    public void setup() {
        entryType = new EntryTypeFactory();
        entry = new BibEntryType(CUSTOM_TYPE, List.of(new BibField(StandardField.AUTHOR, FieldPriority.IMPORTANT)),
                Collections.emptySet());
    }

    @Test
    public void twoNull() {
        boolean Result = entryType.isEqualNameAndFieldBased(null, null);
        assertEquals(true, Result);
    }

    @Test
    public void oneNull() {
        boolean Result = entryType.isEqualNameAndFieldBased(null, entry);
        assertEquals(false, Result);
    }

    @Test
    public void testParseEntryTypePatent() {
        EntryType patent = IEEETranEntryType.Patent;
        assertEquals(patent, EntryTypeFactory.parse("patent"));
    }

    @Test
    public void anInvalidNull() {
        boolean Result = entryType.isEqualNameAndFieldBased(entry, null);
        assertEquals(false, Result);
    }

    @Test
    public void oneNullTrue() {
        boolean Result = entryType.isEqualNameAndFieldBased(entry, entry);
        assertEquals(Objects.equals(entry.getType(), entry.getType())
                && Objects.equals(entry.getRequiredFields(), entry.getRequiredFields())
                && Objects.equals(entry.getOptionalFields(), entry.getOptionalFields())
                && Objects.equals(entry.getSecondaryOptionalFields(), entry.getSecondaryOptionalFields()), Result);
    }
}
