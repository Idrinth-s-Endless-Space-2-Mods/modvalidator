package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class PrimaryController implements Initializable {

    @FXML
    private TextArea output;
    @FXML
    private ChoiceBox<File> modfolder;
    /*@FXML
    private ChoiceBox<String> schema;*/
    private final XMLIterator iterator = new XMLIterator();
    @FXML
    private void validate() {
        output.setText("");
        if (null == modfolder.getValue()) {
            output.setText("Need to choose a mod to check.");
            return;
        }
        iterator.run(modfolder.getValue(), new TextOutputLogger(modfolder.getValue(), output));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var files = new Observed<File>();
        var folder = new File(System.getProperty("user.home") + "/Documents/Endless space 2/Community");
        System.out.println(folder.getAbsoluteFile());
        if (folder.isDirectory()) {
            files.addAll(Arrays.asList(folder.listFiles(new FolderFilter())));
        }
        modfolder.setItems(files);
        /*var schemata = new Observed<String>();
        schemata.add("@internal");
        schema.setItems(schemata);*/
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
