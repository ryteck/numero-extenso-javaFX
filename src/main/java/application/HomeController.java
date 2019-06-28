package application;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import javax.swing.*;

import static application.Conversor.gerarTexto;

public class HomeController {

    private boolean isNegativo;

    private String resposta;

    private String respotaAnterior;

    @FXML
    private JFXTextField inteiro;

    @FXML
    private JFXTextArea txtResposta;

    @FXML
    private void salvaAnterior(){
        respotaAnterior = inteiro.getText();
    }

    @FXML
    private void converter(){

        Conversor.resetar();

        resposta = "";

        if(isNum()){

            if ("".equals(inteiro.getText()) || (inteiro.getLength() == 1 && isNegativo)) {

                txtResposta.setText("VAZIO");

            }
            else {
                config();

                if (isNegativo) {
                    resposta += "MENOS ";
                }

                resposta += gerarTexto();

                txtResposta.setText(resposta);

            }

        }

    }

    //TESTA OS VALORES INSERIDOS PARA QUE SEJAM APENAS NÚMEROS
    private Boolean isNum(){

        String negativa = "-";

        char neg = negativa.charAt(0);

        if ("".equals(inteiro.getText())){ return true; }

        char[] c = inteiro.getText().toCharArray();

        boolean b = false;

        for (int i = 0; i < c.length; i++){

            if (i == 0){
                if (neg == c[i]){
                    isNegativo = true;
                    continue;
                }else{
                    isNegativo = false;
                }
            }

            if (!Character.isDigit(c[i])){
                b = true;
                break;
            }
        }

        if (b) {
            JOptionPane.showMessageDialog(null, "Formato Invalido");
            inteiro.setText(respotaAnterior);
            inteiro.selectRange(inteiro.getLength(), inteiro.getLength());
            return false;
        }

        return true;
    }

    private void config(){

        String s;

        if (isNegativo){ s = inteiro.getText().substring(1); }
        else { s = inteiro.getText(); }

        Conversor.inicialSetup(s);
    }
}
