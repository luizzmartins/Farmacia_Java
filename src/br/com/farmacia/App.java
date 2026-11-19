package br.com.farmacia;

import br.com.farmacia.config.ConexaoDB;
import br.com.farmacia.dao.RemedioDAO;
import br.com.farmacia.dao.UsuarioDAO;
import br.com.farmacia.service.RemedioService;
import br.com.farmacia.service.UsuarioService;
import br.com.farmacia.ui.TelaLogin;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
    public static void main(String[] args) {
        try {
            ConexaoDB banco = ConexaoDB.carregar();
            UsuarioService usuarios = new UsuarioService(new UsuarioDAO(banco));
            RemedioService remedios = new RemedioService(new RemedioDAO(banco));

            SwingUtilities.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
                    // O Swing usa o tema padrão.
                }

                TelaLogin tela = new TelaLogin(usuarios, remedios);
                tela.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                    null, e.getMessage(), "Erro ao iniciar", JOptionPane.ERROR_MESSAGE));
        }
    }
}
