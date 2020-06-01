package de.idrinth.endlessspace2.modvalidator.runner;

import de.idrinth.endlessspace2.modvalidator.DataTransferHelper;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptor;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InitialTest {
    @Mock
    public TextOutputLogger logger;
    @Mock
    public Collection<SimulationDescriptor> values;
    public final static String ROOT = "src/test/resources/de/idrinth/endlessspace2/modvalidator";
    
    @Test
    public void testRunWithNoFolder() {
        System.out.println("run");
        Initial instance = new Initial("", logger);
        instance.run();
        assertEquals(null, DataTransferHelper.instance());
        verify(logger).info("checking requirements");
        verify(logger).info("You need to provide the folder of endless space 2.");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithEmptyFolder() {
        System.out.println("run");
        Initial instance = new Initial(".", logger);
        instance.run();
        assertEquals(null, DataTransferHelper.instance());
        verify(logger).info("checking requirements");
        verify(logger).info("Can't find Simulation folder at the place you pointed to.");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunIncompleteFolder() {
        System.out.println("run");
        Initial instance = new Initial(ROOT+"/game-case-1", logger);
        instance.run();
        assertEquals(null, DataTransferHelper.instance());
        verify(logger).info("checking requirements");
        verify(logger).info("Can't find Schema folder at the place you pointed to.");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunCompleteFolder() {
        System.out.println("run");
        Initial instance = new Initial(ROOT+"/game-case-2", logger);
        instance.run();
        assertNotEquals(null, DataTransferHelper.instance());
        verify(logger).info("checking requirements");
        verify(logger).info("reading in game dir");
        verify(logger).info("done");
        verifyNoMoreInteractions(logger);
    }
    @BeforeEach
    @AfterEach
    public void clean() {
        DataTransferHelper.instance();
    }
}
