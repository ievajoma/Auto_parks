import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AutoRegister extends JFrame{

    private JLabel title1;
    private JLabel title2;
    private JLabel title3;
    private JLabel title4;
    private JLabel labelMarka;
    private JLabel labelModelis;
    private JLabel labelGads;
    private JLabel labelRegNr;
    private JLabel dellabelRegNr;
    private JLabel upLabelRegNr;
    private JTextField inputMarka;
    private JTextField updateMarka;
    private JTextField inputModelis;
    private JTextField updateModelis;
    private JTextField inputGads;
    private JTextField updateGads;
    private JTextField inputRegNr;
    private JTextField delinputRegNr;
    private JTextField upInputRegNr;
    private JButton addAuto;
    private JButton deleteAuto;
    private JButton updateAuto;
    private JButton updateChoice;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JTable autoTable;
    private JScrollPane scrollPane;
    private int update_ID;

    // db connection
    private static final String DB = "jdbc:mysql://localhost:3306/auto_parks";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public AutoRegister() {
        setTitle("Auto reģistrācijas panelis");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // layout
        setLayout(new GridLayout(1, 3));

        // init func
        setupGUI();
        createSQLTable();
        getAutoTable();

    }

    // gui
    public void setupGUI() {
        // ---------------- start init left panel
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0x123456));
        leftPanel.setLayout(null);

        // init add Auto panel
        title1 = new JLabel("CREATE");
        title1.setForeground(new Color(150, 150, 150));
        title1.setBounds(50,40,300,40);
        title1.setFont(new Font("Arial",Font.BOLD,40));
        labelMarka = new JLabel("Auto marka:");
        labelMarka.setForeground(Color.white);
        labelMarka.setBounds(50,100,130,25);
        inputMarka = new JTextField();
        inputMarka.setBounds(190, 100,150,25);
        labelModelis = new JLabel("Auto modelis:");
        labelModelis.setForeground(Color.white);
        labelModelis.setBounds(50,140,130,25);
        inputModelis = new JTextField();
        inputModelis.setBounds(190,140, 150, 25);
        labelGads = new JLabel("Izlaides gads:");
        labelGads.setForeground(Color.white);
        labelGads.setBounds(50,180,130,25);
        inputGads = new JTextField();
        inputGads.setBounds(190, 180,150,25);
        labelRegNr = new JLabel("Reģistrācijas numurs:");
        labelRegNr.setForeground(Color.white);
        labelRegNr.setBounds(50,220,130,25);
        inputRegNr = new JTextField();
        inputRegNr.setBounds(190,220, 150, 25);
        addAuto = new JButton("Pievienot");
        addAuto.setBackground(Color.gray);
        addAuto.setBounds(190,260,150,25);
        addAuto.setForeground(Color.white);

        // init delete Auto panel
        title4 = new JLabel("DELETE");
        title4.setForeground(new Color(150, 150, 150));
        title4.setBounds(50,350,300,40);
        title4.setFont(new Font("Arial",Font.BOLD,40));
        dellabelRegNr = new JLabel("Reģistrācijas numurs:");
        dellabelRegNr.setForeground(Color.white);
        dellabelRegNr.setBounds(50,410,130,25);
        delinputRegNr = new JTextField();
        delinputRegNr.setBounds(190,410, 150, 25);
        deleteAuto = new JButton("Dzēst");
        deleteAuto.setBackground(Color.gray);
        deleteAuto.setBounds(190,450,150,25);
        deleteAuto.setForeground(Color.white);

        leftPanel.add(title1);
        leftPanel.add(labelMarka);
        leftPanel.add(inputMarka);
        leftPanel.add(labelModelis);
        leftPanel.add(inputModelis);
        leftPanel.add(labelGads);
        leftPanel.add(inputGads);
        leftPanel.add(labelRegNr);
        leftPanel.add(inputRegNr);
        leftPanel.add(addAuto);
        leftPanel.add(title4);
        leftPanel.add(dellabelRegNr);
        leftPanel.add(delinputRegNr);
        leftPanel.add(deleteAuto);

        add(leftPanel);
        // -------------- finish init left panel

        // init center panel
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        title4 = new JLabel("READ");
        title4.setForeground(new Color(0x123456));
        title4.setBounds(30,40,300,40);
        title4.setFont(new Font("Arial",Font.BOLD,40));
        centerPanel.add(title4);
        add(centerPanel);

        // -------------- start init right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0x123456));
        rightPanel.setLayout(null);

        // init update choice Auto panel
        title3 = new JLabel("UPDATE");
        title3.setForeground(new Color(150, 150, 150));
        title3.setBounds(50,40,300,40);
        title3.setFont(new Font("Arial",Font.BOLD,40));
        upLabelRegNr = new JLabel("Reģistrācijas numurs:");
        upLabelRegNr.setForeground(Color.white);
        upLabelRegNr.setBounds(50,100,130,25);
        upInputRegNr = new JTextField();
        upInputRegNr.setBounds(190, 100,150,25);
        updateAuto = new JButton("Rediģēt");
        updateAuto.setBackground(Color.gray);
        updateAuto.setBounds(190,140,150,25);
        updateAuto.setForeground(Color.white);

        // init update Auto panel
        labelMarka = new JLabel("Rediģēt marku:");
        labelMarka.setForeground(Color.white);
        labelMarka.setBounds(50,240,130,25);
        labelMarka.setVisible(false);
        updateMarka = new JTextField();
        updateMarka.setBounds(190, 240,150,25);
        updateMarka.setVisible(false);
        labelModelis = new JLabel("Rediģēt modeli:");
        labelModelis.setForeground(Color.white);
        labelModelis.setBounds(50,280,130,25);
        labelModelis.setVisible(false);
        updateModelis = new JTextField();
        updateModelis.setBounds(190,280, 150, 25);
        updateModelis.setVisible(false);
        labelGads = new JLabel("Rediģēt gadu:");
        labelGads.setForeground(Color.white);
        labelGads.setBounds(50,320,130,25);
        labelGads.setVisible(false);
        updateGads = new JTextField();
        updateGads.setBounds(190, 320,150,25);
        updateGads.setVisible(false);
        updateChoice = new JButton("Iesniegt");
        updateChoice.setBackground(Color.gray);
        updateChoice.setBounds(190,360,150,25);
        updateChoice.setForeground(Color.white);
        updateChoice.setVisible(false);

        rightPanel.add(labelMarka);
        rightPanel.add(updateMarka);
        rightPanel.add(labelModelis);
        rightPanel.add(updateModelis);
        rightPanel.add(labelGads);
        rightPanel.add(updateGads);
        rightPanel.add(updateChoice);
        rightPanel.add(title3);
        rightPanel.add(upLabelRegNr);
        rightPanel.add(upInputRegNr);
        rightPanel.add(updateAuto);

        add(rightPanel);
        // -------------------- finish init right panel

        // actions
        addAuto.addActionListener(e -> addAuto());
        deleteAuto.addActionListener(e -> deleteAuto());
        updateAuto.addActionListener(e -> {
            update_ID = showUpdate();
        });
        updateChoice.addActionListener(e -> initUpdate(update_ID));

    }

    // create SQL table
    public void createSQLTable() {

        String sql = "CREATE TABLE IF NOT EXISTS auto " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "marka VARCHAR(50) NOT NULL, " +
                "modelis VARCHAR(50) NOT NULL, " +
                "gads INT NOT NULL, " +
                "reg_nr VARCHAR(50) NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datubāzes error: " + e.getMessage());
        }
    }

    // get Auto table
    private void getAutoTable() {
        String[] columnNames = {"Marka", "Modelis", "Gads", "Reģ. Nr."};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // select query
        String sql = "SELECT * FROM auto";

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("marka"),
                        rs.getString("modelis"),
                        rs.getString("gads"),
                        rs.getString("reg_nr")
                };
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ielādes kļūda: " + e.getMessage());
        }

        autoTable = new JTable(tableModel);
        scrollPane = new JScrollPane(autoTable);
        scrollPane.setBounds(0,100,400,380);

        centerPanel.setLayout(null);
        centerPanel.removeAll();
        centerPanel.add(scrollPane);
        centerPanel.add(title4);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // add Auto
    private void addAuto() {
        String marka = inputMarka.getText().trim();
        String modelis = inputModelis.getText().trim();
        String gads = inputGads.getText().trim();
        String regNr = inputRegNr.getText().trim();

        if (marka.isEmpty() || modelis.isEmpty() || gads.isEmpty() || regNr.isEmpty()) {
            inputMarka.setText("");
            inputModelis.setText("");
            inputGads.setText("");
            inputRegNr.setText("");
            JOptionPane.showMessageDialog(this, "Nav pievienots! Aizpildiet lauciņus!");
            return;
        }

        String sql = "INSERT INTO auto (marka, modelis, gads, reg_nr) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, marka);
            pstmt.setString(2, modelis);
            pstmt.setString(3, gads);
            pstmt.setString(4, regNr);
            pstmt.executeUpdate();

            getAutoTable();
            inputMarka.setText("");
            inputModelis.setText("");
            inputGads.setText("");
            inputRegNr.setText("");
            JOptionPane.showMessageDialog(this, "Auto veiksmīgi pievienots!");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Pievienošanas kļūda: " + e.getMessage());
        }
    }

    // delete Auto
    private void deleteAuto() {
        String regNr = delinputRegNr.getText().trim();
        String sql = "DELETE FROM auto WHERE reg_nr = ?";

        if (regNr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nav DZĒSTS! Aizpildiet lauciņu!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, regNr);
            int rowsAffected = pstmt.executeUpdate();
            getAutoTable();
            delinputRegNr.setText("");

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Auto veiksmīgi DZĒSTS no datu bāzes!");
            } else {
                JOptionPane.showMessageDialog(this, "Noradītais auto netika atrasts!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Dzēšanas kļūda: " + e.getMessage());
        }
    }

    // show update choice
    private int showUpdate() {
        String upRegNr = upInputRegNr.getText().trim();
        String sql = "SELECT * FROM auto WHERE reg_nr = ?";

        if (upRegNr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aizpildiet lauciņu!");
            return 0;
        }

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, upRegNr);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                labelMarka.setVisible(true);
                updateMarka.setVisible(true);
                updateMarka.setText(rs.getString("marka"));
                labelModelis.setVisible(true);
                updateModelis.setVisible(true);
                updateModelis.setText(rs.getString("modelis"));
                labelGads.setVisible(true);
                updateGads.setVisible(true);
                updateGads.setText(rs.getString("gads"));
                updateChoice.setVisible(true);
                upInputRegNr.setText("");

                return rs.getInt("id");
            } else {
                upInputRegNr.setText("");
                JOptionPane.showMessageDialog(this, "Norādītais auto netika atrasts!");
                return 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ielādes kļūda: " + e.getMessage());
            return 0;
        }
    }

    // init update choice
    private void initUpdate(int selectID) {
        String newMarka = updateMarka.getText().trim();
        String newModelis = updateModelis.getText().trim();
        String newGads = updateGads.getText().trim();

        if (newMarka.isEmpty() || newModelis.isEmpty() || newGads.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Visiem lauciņiem jābūt aizpildītiem!");
            return;
        }

        String sql = "UPDATE auto SET marka = ?, modelis = ?, gads = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newMarka);
            pstmt.setString(2, newModelis);
            pstmt.setString(3, newGads);
            pstmt.setInt(4, selectID);
            pstmt.executeUpdate();

            getAutoTable();
            updateMarka.setText("");
            updateModelis.setText("");
            updateGads.setText("");

            labelMarka.setVisible(false);
            updateMarka.setVisible(false);
            labelModelis.setVisible(false);
            updateModelis.setVisible(false);
            labelGads.setVisible(false);
            updateGads.setVisible(false);
            updateChoice.setVisible(false);

            JOptionPane.showMessageDialog(this, "Dati veiksmīgi izmainīti!");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Pievienošanas kļūda: " + e.getMessage());
        }
    }
}
