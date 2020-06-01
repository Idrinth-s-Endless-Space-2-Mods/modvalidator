package de.idrinth.endlessspace2.modvalidator;

import java.io.File;

class DataTransferHelper {
    private static XMLIterator iterator;
    private static SimulationDescriptors rootList;
    private static File workshopDir;
    private static File gameDir;

    public static void setIterator(XMLIterator iterator) {
        DataTransferHelper.iterator = iterator;
    }

    public static void setRootList(SimulationDescriptors rootList) {
        DataTransferHelper.rootList = rootList;
    }

    public static void setWorkshopDir(File workshopDir) {
        DataTransferHelper.workshopDir = workshopDir;
    }
    public static void setGameDir(File gameDir) {
        DataTransferHelper.gameDir = gameDir;
    }

    public static XMLIterator iterator() {
        return iterator;
    }
    public static SimulationDescriptors rootList() {
        return rootList;
    }
    public static File workshopDir() {
        return workshopDir;
    }
    public static File gameDir() {
        return gameDir;
    }
}
