package de.idrinth.endlessspace2.modvalidator;

import java.io.File;

class Data {
    private static XMLIterator iterator;
    private static SimulationDescriptors rootList;
    private static File workshopDir;

    public static void setIterator(XMLIterator iterator) {
        Data.iterator = iterator;
    }

    public static void setRootList(SimulationDescriptors rootList) {
        Data.rootList = rootList;
    }

    public static void setWorkshopDir(File workshopDir) {
        Data.workshopDir = workshopDir;
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
}