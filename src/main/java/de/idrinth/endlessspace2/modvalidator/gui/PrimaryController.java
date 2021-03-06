package de.idrinth.endlessspace2.modvalidator.gui;

import de.idrinth.endlessspace2.modvalidator.DataTransferHelper;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.logger.TextOutputLogger;
import de.idrinth.endlessspace2.modvalidator.runner.Validation;
import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

public class PrimaryController extends ThreaddedController implements Initializable {
    @FXML
    private ChoiceBox<File> modfolder;
    private ValidatingXMLIterator iterator;
    private SimulationDescriptors rootList;
    @FXML
    private void validate() {
        execute(new Validation(modfolder.getValue(), iterator, rootList, new TextOutputLogger(modfolder.getValue(), new TAWrapper(output))));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var helper = DataTransferHelper.instance();
        iterator = helper.iterator();
        rootList = helper.rootList();
        var files = new Observed<File>();
        var folder = new File(System.getProperty("user.home") + "/Documents/Endless space 2/Community");
        if (folder.isDirectory()) {
            files.addAll(Arrays.asList(folder.listFiles(new FolderFilter())));
        }
        var workshop = helper.workshopDir();
        if (workshop.isDirectory()) {
            files.addAll(Arrays.asList(workshop.listFiles(new FolderFilter())));
        }
        files.add(helper.gameDir());
        modfolder.setConverter(new FileConverter());
        modfolder.setItems(files);
    }
    private class FileConverter extends StringConverter<File> {
        private final HashSet<Identifier> ids = new HashSet<>();
        @Override
        public String toString(File file) {
            for (var id : ids) {
                if (id.file == file) {
                    return id.path;
                }
            }
            try {
                var id = new Identifier(file);
                ids.add(id);
                return id.id();
            } catch (IOException ex) {
                return "";
            }
        }

        @Override
        public File fromString(String ident) {
            for (var id : ids) {
                if (id.id().equals(ident)) {
                    return id.file;
                }
            }
            return null;
        }
        private class Identifier {
            private final String path;
            private final String full;
            private final String name;
            private final File file;

            public Identifier(File file) throws IOException {
                full = file.getCanonicalPath();
                if (full.contains("workshop")) {
                    path = "workshop://"+full.substring(full.lastIndexOf("workshop")+24);
                    name = getFirstFileOfDir(file);
                } else if(full.contains("Community")) {
                    path = "local://"+full.substring(full.lastIndexOf("Community")+10);
                    name = getFirstFileOfDir(file);
                } else if(full.endsWith("Public")) {
                    path = "game://Endless Space 2";
                    name = "Base";
                } else {
                    throw new IOException(full+" can't be recognised as a folder");
                }
                this.file = file;
            }
            private String getFirstFileOfDir(File dir) {
                var files = dir.listFiles(new XMLNotRegistry());
                if (files == null || files.length==0) {
                    return "";
                }
                var first = files[0];
                if (first == null) {
                    return "";
                }
                return first.getName().replace(".xml", "");
            }
            public String id() {
                return String.format("%s%s%s", path, "@", name);
            }

            @Override
            public int hashCode() {
                return 41 * 7 + Objects.hashCode(this.full);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                final Identifier other = (Identifier) obj;
                return Objects.equals(this.full, other.full);
            }
        }
    }
    class XMLNotRegistry implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".xml") && !name.equalsIgnoreCase("Registry.xml");
        }
    }
    private class FolderFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
        
    }
    private class Observed<Type> extends ObservableListBase<Type> {
        private final ArrayList list = new ArrayList<Type>();
        @Override
        public Type get(int index) {
            return (Type) list.get(index);
        }

        @Override
        public int size() {
            return list.size();
        }
        @Override
        public void add(int position, Type el) {
            list.add(position, el);
        }
    }
}
