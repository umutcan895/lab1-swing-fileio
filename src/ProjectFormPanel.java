import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
    private JTextField projectNameField;
    private JTextField projectLeaderField;
    private JTextField startDateField;
    private JComboBox<String> teamSizeCombo;
    private JComboBox<String> projectTypeCombo;
    private JButton saveButton;
    private JButton clearButton;

    public ProjectFormPanel(){


        String[] teamSize = {"1-3","4-6","7-10","10+"};
        String[] projectType = {"Web","Mobule","Desktop","Api"};

        saveButton = new JButton("Save");
        clearButton = new  JButton("Clear");

        teamSizeCombo = new JComboBox<>(teamSize);
        projectTypeCombo = new JComboBox<>(projectType);
        projectNameField = new JTextField();
        projectLeaderField = new JTextField();
        startDateField = new JTextField();

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Project Name"));
        add(projectNameField);
        add(new JLabel("Project Leader"));
        add(projectLeaderField);
        add(new JLabel("Team Size"));
        add(teamSizeCombo);
        add(new JLabel("Project Type"));
        add(projectTypeCombo);
        add(new JLabel("Start Date"));
        add(startDateField);
        add(saveButton);
        add(clearButton);

        clearButton.addActionListener(e -> clearFields());
        saveButton.addActionListener(e -> saveProject());

    }


    private void clearFields() {
        projectNameField.setText("");
        projectLeaderField.setText("");
        startDateField.setText("");
        teamSizeCombo.setSelectedIndex(0);
        projectTypeCombo.setSelectedIndex(0);
    }



        private void saveProject() {

            String projectName = projectNameField.getText().trim();
            String projectLeader = projectLeaderField.getText().trim();
            String startDate = startDateField.getText().trim();

            String teamSize = (String) teamSizeCombo.getSelectedItem();
            String projectType = (String) projectTypeCombo.getSelectedItem();


        if (projectName.isEmpty() || projectLeader.isEmpty() || startDate.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

            String recordTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            String data ="=== Project Entry ===\n"
                    + "Project Name : " + projectName + "\n" +
                    "Project Leader : " + projectLeader + "\n" +
                    "Team Size : " + teamSize + "\n" +
                    "Project Type : " + projectType + "\n" +
                    "Start Date : " + startDate + "\n" +
                    "Record Time : " + recordTime + "\n" +
                    "====================\n\n";

            try (FileWriter writer = new FileWriter("projects.txt", true)) {
                writer.write(data);
                JOptionPane.showMessageDialog(this, "Project saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file!");
            }

    }
}

