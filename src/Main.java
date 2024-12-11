public class Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            AutoRegister register = new AutoRegister();

            register.setVisible(true);
        });
    }
}