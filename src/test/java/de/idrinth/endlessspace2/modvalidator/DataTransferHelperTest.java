package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTransferHelperTest {
    @AfterEach
    @BeforeEach
    public void clean() {
        DataTransferHelper.setGameDir(null);
        DataTransferHelper.setIterator(null);
        DataTransferHelper.setRootList(null);
        DataTransferHelper.setWorkshopDir(null);
    }

    @Test
    public void testIterator() {
        System.out.println("iterator");
        XMLIterator result = new XMLIterator(new File("."));
        assertEquals(null, DataTransferHelper.iterator());
        DataTransferHelper.setIterator(result);
        assertEquals(result, DataTransferHelper.iterator());
    }

    @Test
    public void testRootList() {
        System.out.println("rootList");
        SimulationDescriptors result = new SimulationDescriptors();
        assertEquals(null, DataTransferHelper.rootList());
        DataTransferHelper.setRootList(result);
        assertEquals(result, DataTransferHelper.rootList());
    }

    @Test
    public void testWorkshopDir() {
        System.out.println("workshopDir");
        File result = new File(".");
        assertEquals(null, DataTransferHelper.workshopDir());
        DataTransferHelper.setWorkshopDir(result);
        assertEquals(result, DataTransferHelper.workshopDir());
    }

    @Test
    public void testGameDir() {
        System.out.println("gameDir");
        File result = new File(".");
        assertEquals(null, DataTransferHelper.gameDir());
        DataTransferHelper.setGameDir(result);
        assertEquals(result, DataTransferHelper.gameDir());
    }
}
