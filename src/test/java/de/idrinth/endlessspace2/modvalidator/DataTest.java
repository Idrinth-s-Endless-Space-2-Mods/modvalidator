package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTest {
    @AfterEach
    @BeforeEach
    public void clean() {
        Data.setGameDir(null);
        Data.setIterator(null);
        Data.setRootList(null);
        Data.setWorkshopDir(null);
    }

    @Test
    public void testIterator() {
        System.out.println("iterator");
        XMLIterator result = new XMLIterator(new File("."));
        assertEquals(null, Data.iterator());
        Data.setIterator(result);
        assertEquals(result, Data.iterator());
    }

    @Test
    public void testRootList() {
        System.out.println("rootList");
        SimulationDescriptors result = new SimulationDescriptors();
        assertEquals(null, Data.rootList());
        Data.setRootList(result);
        assertEquals(result, Data.rootList());
    }

    @Test
    public void testWorkshopDir() {
        System.out.println("workshopDir");
        File result = new File(".");
        assertEquals(null, Data.workshopDir());
        Data.setWorkshopDir(result);
        assertEquals(result, Data.workshopDir());
    }

    @Test
    public void testGameDir() {
        System.out.println("gameDir");
        File result = new File(".");
        assertEquals(null, Data.gameDir());
        Data.setGameDir(result);
        assertEquals(result, Data.gameDir());
    }
}
