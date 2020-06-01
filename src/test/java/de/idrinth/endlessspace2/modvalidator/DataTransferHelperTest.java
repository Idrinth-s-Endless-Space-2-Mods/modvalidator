package de.idrinth.endlessspace2.modvalidator;

import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataTransferHelperTest {
    @Mock
    public File file;
    @Mock
    public SimulationDescriptors list;
    @Mock
    public ValidatingXMLIterator iterator;
    @AfterEach
    @BeforeEach
    public void clean() {
        DataTransferHelper.instance();
    }

    @Test
    public void testIterator() {
        System.out.println("iterator");
        var result = new ValidatingXMLIterator(null, null);
        var helper = new DataTransferHelper(result, list, file, file);
        assertEquals(result, helper.iterator());
    }

    @Test
    public void testRootList() {
        System.out.println("rootList");
        var result = new SimulationDescriptors();
        var helper = new DataTransferHelper(iterator, result, file, file);
        assertEquals(result, helper.rootList());
    }

    @Test
    public void testWorkshopDir() {
        System.out.println("workshopDir");
        File result = new File(".");
        var helper = new DataTransferHelper(iterator, list, result, file);
        assertEquals(result, helper.workshopDir());
    }

    @Test
    public void testGameDir() {
        System.out.println("gameDir");
        File result = new File(".");
        var helper = new DataTransferHelper(iterator, list, file, result);
        assertEquals(result, helper.gameDir());
    }

    @Test
    public void testCreateInstance() {
        System.out.println("Create+Instance");
        assertEquals(null, DataTransferHelper.instance());
        DataTransferHelper.create(iterator, list, file, file);
        var result = DataTransferHelper.instance();
        assertNotEquals(null, result);
        assertEquals(file, result.gameDir());
        assertEquals(file, result.workshopDir());
        assertEquals(iterator, result.iterator());
        assertEquals(list, result.rootList());
        assertEquals(null, DataTransferHelper.instance());
    }
}
