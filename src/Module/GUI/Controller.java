package Module.GUI;

import Module.Classes.*;
import Module.Exceptions.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Controller implements Initializable {
    @FXML
    private final Alert PEEalert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private final Alert PIalert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private final Alert PDalert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private final Alert MFPEalert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private final Alert SIEalert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private final Alert contaCriadaalert = new Alert(Alert.AlertType.CONFIRMATION);
    RepositorioUsuario mainRepository;
    MyTwitter mainTwitter;
    @FXML
    private Button btTweet;
    @FXML
    private TextField accountNameTextField;
    @FXML
    private TextField tweetText;
    @FXML
    private TextField codeField;
    @FXML
    private TextArea timelineArea;
    @FXML
    private Button btAccountCreate;
    @FXML
    private ComboBox selectorBox;
    @FXML
    private Label selectedUser;
    @FXML
    private RadioButton CPFregister;
    @FXML
    private RadioButton CNPJregister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contaCriadaalert.setTitle("Conta criada");
        contaCriadaalert.setContentText("Conta criada com sucesso!");
        PEEalert.setTitle("Erro");
        PEEalert.setHeaderText("PEException");
        PEEalert.setContentText("Já existe uma conta cadastrada com esse nome");

        PIalert.setTitle("Erro");
        PIalert.setHeaderText("PIException");
        PIalert.setContentText("O perfil não existe!");

        PDalert.setTitle("Erro");
        PDalert.setHeaderText("PDException");
        PDalert.setContentText("O perfil esta inativo!");

        MFPEalert.setTitle("Erro");
        MFPEalert.setHeaderText("MFPException");
        MFPEalert.setContentText("A mensagem não está nos limites de caracteres!");

        SIEalert.setTitle("Erro");
        SIEalert.setHeaderText("SIException");
        SIEalert.setContentText("A relação seguidor/seguido é invalida!");


        mainRepository = new RepositorioUsuario();
        mainTwitter = new MyTwitter(mainRepository);
        selectorBox.getItems().addAll(
                "Buscar Perfil",
                "Criar Perfil",
                "Seguir",
                "Cancelar Perfil",
                "Atualizar Perfil",
                "Adquirir Timeline",
                "Adquirir Tweets",
                "Adquirir Seguidores",
                "Adquirir Seguidos",
                "Adquirir num. de seguidores"
        );

    }


    @FXML
    public void onBtnAccountCreate() {
        String selection = selectorBox.getValue().toString();
        String tempString = accountNameTextField.getText();
        String fullTimeLine;
        switch (selection) {
            case "Buscar Perfil":
                String tempCN = "";
                Perfil tempPerfil = mainRepository.busca(tempString);
                if (tempPerfil == null) {
                    PIalert.showAndWait();
                }
                if(tempPerfil.isAtivo() == false){
                    PDalert.showAndWait();
                }
                if (tempPerfil instanceof PessoaFisica) {
                    tempCN = ((PessoaFisica) tempPerfil).getCpf();
                    selectedUser.setText(tempString);
                    codeField.setText(tempCN);
                    CNPJregister.setSelected(false);
                    CPFregister.setSelected(true);
                } else if (tempPerfil instanceof PessoaJuridica) {
                    tempCN = ((PessoaJuridica) tempPerfil).getCnpj();
                    selectedUser.setText(tempString);
                    codeField.setText(tempCN);
                    CPFregister.setSelected(false);
                    CNPJregister.setSelected(true);
                }

                break;
            case "Criar Perfil":
                boolean CPFbool = CPFregister.isSelected();
                boolean CNPJbool = CPFregister.isSelected();
                String cadastroNacional = codeField.getText();
                System.out.println(tempString);
                if (CPFbool == true) {
                    PessoaFisica tempAccount = new PessoaFisica(tempString);
                    tempAccount.setCpf(cadastroNacional);
                    tempAccount.setAtivo(true);
                    System.out.println(tempAccount.isAtivo());
                    try {
                        mainTwitter.criarPerfil(tempAccount);
                        selectedUser.setText(tempString);
                        codeField.setText(cadastroNacional);
                        tweetText.setText("Conta criada com sucesso!");
                    } catch (PEException e) {
                        PEEalert.showAndWait();
                    }
                } else {
                    PessoaJuridica tempAccount = new PessoaJuridica(tempString);
                    tempAccount.setCnpj(cadastroNacional);
                    tempAccount.setAtivo(true);
                    try {
                        mainTwitter.criarPerfil(tempAccount);
                        selectedUser.setText(tempString);
                        codeField.setText(cadastroNacional);
                        tweetText.setText("Conta criada com sucesso!");
                    } catch (PEException e) {
                        PEEalert.showAndWait();
                    }
                }
                break;

            case "Cancelar Perfil":
                try {
                    mainTwitter.cancelarPerfil(tempString);
                    tweetText.setText("Perfil cancelado com sucesso!");
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                break;
            case "Adquirir Timeline":
                String userName = selectedUser.getText();
                Vector<Tweet> timeLine = new Vector<Tweet>();
                fullTimeLine = "";
                try {
                    timeLine = mainTwitter.timeline(userName);
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                for (Tweet tweet : timeLine) {
                    fullTimeLine = fullTimeLine + tweet.getUsuario() + "\t" + tweet.getMensagem() + "\n";
                }
                timelineArea.setText(fullTimeLine);
                break;

            case "Adquirir Tweets":
                userName = selectedUser.getText();
                Vector<Tweet> tweets = new Vector<Tweet>();
                fullTimeLine = "";
                try {
                    tweets = mainTwitter.tweets(userName);
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                for (Tweet tweet : tweets) {
                    fullTimeLine = fullTimeLine + tweet.getUsuario() + "\t" + tweet.getMensagem() + "\n";
                }
                timelineArea.setText(fullTimeLine);
                break;

            case "Adquirir Seguidores":
                userName = selectedUser.getText();
                String userString = "";
                Vector<Perfil> users = new Vector<Perfil>();
                try {
                    users = mainTwitter.seguidores(userName);
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                for (Perfil user : users) {
                    userString += user.getUsuario() + "\n";
                }
                timelineArea.setText(userString);
                break;

            case "Adquirir Seguidos":
                userName = selectedUser.getText();
                userString = "";
                users = new Vector<Perfil>();
                try {
                    users = mainTwitter.seguidos(userName);
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                for (Perfil user : users) {
                    userString += user.getUsuario() + "\n";
                }
                timelineArea.setText(userString);
                break;

            case "Seguir":
                String follower = selectedUser.getText();
                String followed = accountNameTextField.getText();
                try {
                    mainTwitter.seguir(follower, followed);
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                } catch (SIException e) {
                    SIEalert.showAndWait();
                }
            case "Adquirir num. de seguidores":
                String textFollower = "";
                try {
                    int numFollower = mainTwitter.numeroSeguidores(accountNameTextField.getText());
                    textFollower = "Sua conta possui: " + numFollower + " seguidores";
                } catch (PIException e) {
                    PIalert.showAndWait();
                } catch (PDException e) {
                    PDalert.showAndWait();
                }
                timelineArea.setText(textFollower);
                break;
            default:
        }
    }

    @FXML
    public void onBtnTweet() {
        String message = tweetText.getText();
        String userName = accountNameTextField.getText();
        try {
            mainTwitter.tweetar(userName, message);
            System.out.println(userName);
            tweetText.setText(tweetText.getText() + "   " + "Tweet com sucesso!");
        } catch (PIException e) {
            PIalert.showAndWait();
        } catch (MFPException e) {
            MFPEalert.showAndWait();
        }
    }

    @FXML
    void onSelectorBoxInteract() {
        btAccountCreate.setText(selectorBox.getValue().toString());
    }
}
