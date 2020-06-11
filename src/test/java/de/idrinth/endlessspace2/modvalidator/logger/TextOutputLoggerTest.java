package de.idrinth.endlessspace2.modvalidator.logger;

import de.idrinth.endlessspace2.modvalidator.gui.TAWrapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TextOutputLoggerTest {
    @Mock
    public TAWrapper out;
    @Mock
    public File file;

    @Test
    public void testWarn() throws IOException {
        System.out.println("warn");
        TextOutputLogger instance = new TextOutputLogger("ex", out);
        when(file.getCanonicalPath()).thenReturn("ex/am/ple.xml");
        instance.warn(file, "Message");
        verify(out, times(1)).clear();
        verify(out, times(1)).append("[WARNING] /am/ple.xml: Message\n");
        verifyNoMoreInteractions(out);
    }

    @Test
    public void testErrorCalledWithFileAndString() throws IOException {
        System.out.println("error");
        TextOutputLogger instance = new TextOutputLogger("ex", out);
        when(file.getCanonicalPath()).thenReturn("ex/am/ple.xml");
        instance.error(file, "Message");
        verify(out, times(1)).clear();
        verify(out, times(1)).append("[ERROR] /am/ple.xml: Message\n");
        verifyNoMoreInteractions(out);
    }

    @Test
    public void testErrorCalledWithFileAndThrowable() throws IOException {
        System.out.println("error");
        TextOutputLogger instance = new TextOutputLogger("ex", out);
        var thrown = new Exception("Exception");
        when(file.getCanonicalPath()).thenReturn("ex/am/ple.xml");
        instance.error(file, thrown);
        verify(out, times(1)).clear();
        verify(out, times(1)).append("[ERROR] /am/ple.xml: Exception\n");
        verifyNoMoreInteractions(out);
    }

    @Test
    public void testDebug() throws IOException {
        System.out.println("debug");
        TextOutputLogger instance = new TextOutputLogger("ex", out);
        when(file.getCanonicalPath()).thenReturn("ex/am/ple.xml");
        instance.debug(file, "Test");
        verify(out, times(1)).clear();
        verifyNoMoreInteractions(out);
    }

    @Test
    public void testInfo() throws IOException {
        System.out.println("info");
        TextOutputLogger instance = new TextOutputLogger("ex", out);
        instance.info("INFO");
        verify(out, times(1)).clear();
        verify(out, times(1)).append("[INFO] PROCESS: INFO\n");
        verifyNoMoreInteractions(out);
    }

    @Test
    public void testWarnWithBrokenFile() throws IOException {
        System.out.println("info");
        TextOutputLogger instance = new TextOutputLogger(new File("./uuuu"), out);
        instance.warn(new File("."), "INFO");
        verify(out, times(1)).clear();
        verify(out, times(1)).append("[WARNING] "+new File(".").getCanonicalPath()+": INFO\n");
        verifyNoMoreInteractions(out);
    }
}
