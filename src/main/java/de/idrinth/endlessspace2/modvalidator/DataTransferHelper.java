package de.idrinth.endlessspace2.modvalidator;

import java.io.File;

class DataTransferHelper {
    private static DataTransferHelper instance;
    private final XMLIterator iterator;
    private final SimulationDescriptors rootList;
    private final File workshopDir;
    private final File gameDir;

    public DataTransferHelper(XMLIterator iterator, SimulationDescriptors rootList, File workshopDir, File gameDir) {
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

    public static void create(XMLIterator iterator, SimulationDescriptors rootList, File workshopDir, File gameDir) {
        instance = new DataTransferHelper(iterator, rootList, workshopDir, gameDir);
    }

    public XMLIterator iterator() {
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
