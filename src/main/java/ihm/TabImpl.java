package ihm;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

class TabImpl extends GridPane {

    private static int rowIndex;
    private final List<Section> sections = new ArrayList<>();

    void newSection(String name, String label, Node... children) {
        Label sectionLabel = new Label(label);
        sectionLabel.setMaxSize(150, 40);
        sectionLabel.setMinSize(150, 40);
        HBox box = new HBox(10);
        box.getChildren().addAll(children);
        setConstraints(sectionLabel, 0, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        setConstraints(box, 1, rowIndex++);
        box.setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(sectionLabel, box);
        sections.add(new Section(name, sectionLabel, box));
    }

    private List<Section> getSections() {
        return sections;
    }

    protected Section getSection(String name) {
        Section result = null;
        for (Section section :
                getSections()) {
            if (name.equals(section.getName())) {
                result = section;
            }
        }
        return result;
    }

    protected void updateSection(String name, String label, Node... children) {
        Section sectionOld = getSection(name);
        if (sectionOld != null) {
            Section sectionNew = sectionOld;
            sectionNew.getLabel().setText(label);
            HBox box = new HBox(10);
            box.getChildren().addAll(children);
            setConstraints(box, 1, rowIndex++);
            box.setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(sectionNew.getLabel(), box);
            sections.set(sections.indexOf(sectionOld), sectionNew);
        } else {
            Label sectionLabel = new Label(label);
            sectionLabel.setMaxSize(150, 40);
            sectionLabel.setMinSize(150, 40);
            HBox box = new HBox(10);
            box.setFillHeight(false);
            box.getChildren().addAll(children);
            setConstraints(sectionLabel, 0, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            setConstraints(box, 1, rowIndex++);
            box.setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(sectionLabel, box);
            sections.add(new Section(name, sectionLabel, box));
        }
    }

    protected static class Section {
        final String name;
        Label label;
        HBox box;

        Section(String name, Label label, HBox box) {
            this.name = name;
            this.label = label;
            this.box = box;
        }

        String getName() {
            return name;
        }

        Label getLabel() {
            return label;
        }

        HBox getBox() {
            return box;
        }

        public void setLabel(Label label) {
            this.label = label;
        }

        public void setBox(HBox box) {
            this.box = box;
        }
    }
}
