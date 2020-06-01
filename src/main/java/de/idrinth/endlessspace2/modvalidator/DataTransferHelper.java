package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;

public class DataTransferHelper {
    private static DataTransferHelper instance;
    private final ValidatingXMLIterator iterator;
    private final SimulationDescriptors rootList;
    private final File workshopDir;
    private final File gameDir;

    public DataTransferHelper(ValidatingXMLIterator iterator, SimulationDescriptors rootList, File workshopDir, File gameDir) {
        this.iterator = iterator;
        this.rootList = rootList;
        this.workshopDir = workshopDir;
        this.gameDir = gameDir;
    }

    public static DataTransferHelper instance() {
        var local = instance;
        instance = null;
        return local;
    }

    public static void create(ValidatingXMLIterator iterator, SimulationDescriptors rootList, File workshopDir, File gameDir) {
        instance = new DataTransferHelper(iterator, rootList, workshopDir, gameDir);
    }

    public ValidatingXMLIterator iterator() {
        return iterator;
    }
    public SimulationDescriptors rootList() {
        return rootList;
    }
    public File workshopDir() {
        return workshopDir;
    }
    public File gameDir() {
        return gameDir;
    }
}
