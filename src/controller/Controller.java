package controller;

import Dao.Dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {

    Connection conexao = null;
    // criando variaveis especiais para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Controller() {
        conexao = Dao.connector();
    }

    public void Create() {
        String nome, email, sexo, nascimento;
        String telefone;
        //Iniciando comando de recepção de dados
        Scanner prompt = new Scanner(System.in);

        //Recebendo dados
        System.out.println("Informe o nome do usuario");
        nome = prompt.nextLine();

        System.out.println("Informe o email");
        email = prompt.nextLine();

        System.out.println("Informe o sexo");
        sexo = prompt.nextLine();

        System.out.println("Informe a data de nascimento");
        nascimento = prompt.nextLine();

        System.out.println("Informe o telefone");
        telefone = prompt.nextLine();

        //Criando sql
        String sql = "insert into People (nomePeople,emailPeople,sexoPeople,telefonePeople,nascimentoPeople,criacaoRegistro,atualizacaoRegistro)" +
                "values ('" + nome + "','" + email + "','" + sexo + "','" + telefone + "','" + nascimento  + "',now(),now())";
        try {
            pst = conexao.prepareStatement(sql);
            if (nome.isEmpty() || email.isEmpty() || sexo.isEmpty() || nascimento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
                System.out.println("Informe o nome do usuario");
                nome = prompt.nextLine();

                System.out.println("Informe o email");
                email = prompt.nextLine();

                System.out.println("Informe o sexo");
                sexo = prompt.nextLine();

                System.out.println("Informe a data de nascimento");
                nascimento = prompt.nextLine();
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
            System.out.println(exception);
        }
    }

    public void Update() {
        String nome, telefone;
        int idPeople;

        Scanner prompt = new Scanner(System.in);
        //Recebendo dados

        System.out.println("Informe o novo nome");
        nome = prompt.nextLine();

        System.out.println("Informe o novo telefone");
        telefone = prompt.nextLine();

        System.out.println("Informe o id do nome que deseja alterar");
        idPeople = prompt.nextInt();

        String sql = "update People set nomePeople = '" + nome + "', telefonePeople = '" + telefone + "', atualizacaoRegistro = now() where idPeople = " + idPeople + ";";
        try {
            pst = conexao.prepareStatement(sql);
            if (nome.isEmpty() || telefone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");

                //Recebendo dados
                System.out.println("Informe o id do nome que deseja alterar");
                idPeople = prompt.nextInt();

                System.out.println("Informe o novo nome");
                nome = prompt.nextLine();

                System.out.println("Informe o novo telefone");
                telefone = prompt.nextLine();
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,exception );
        }
    }
        public void Delete () {
            int idPeople;
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar esse campo?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                Scanner prompt = new Scanner(System.in);
                //Recebendo dados
                System.out.println("Informe o id do nome que deseja deletar");
                idPeople = prompt.nextInt();

                String sql = "delete from People where idPeople = " + idPeople + ";";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Dado excluido com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }

        public void GetAll () throws SQLException {
            String  name,
                    nomePeople,
                    emailPeople,
                    sexoPeople,
                    nascimentoPeople,
                    telefonePeople,
                    criaçãoRegistro,
                    atualizacaoRegistro;
            int idPeople;
            String sql = "select * from People";
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

            } catch (Exception e) {
            }
           while (rs.next()){
                    System.out.println("---------------------------------------------------------------------------------------------");
                    System.out.println("Id:" + (idPeople = rs.getInt("idPeople")));
                    System.out.println("Nome:" + (nomePeople = rs.getString("nomePeople").toString()));
                    System.out.println("Email:" + (emailPeople = rs.getString("emailPeople").toString()));
                    System.out.println("Sexo:" + (sexoPeople = rs.getString("sexoPeople").toString()));
                    System.out.println("Nascimento:" + (nascimentoPeople = rs.getString("nascimentoPeople").toString()));
                    System.out.println("Telefone:" + (telefonePeople = rs.getString("telefonePeople").toString()));
                    System.out.println("Data de Criação do Registro:" + (criaçãoRegistro = rs.getDate("criacaoRegistro").toString()));
                    System.out.println("Data da utima alteração do Registro:" + (atualizacaoRegistro = rs.getDate("atualizacaoRegistro").toString()));
                    System.out.println("---------------------------------------------------------------------------------------------");
                }
            }

        public void GetAllByEmailOrName () throws SQLException {
            String name, email;
            String  nomePeople,
                    emailPeople,
                    sexoPeople,
                    nascimentoPeople,
                    telefonePeople,
                    criaçãoRegistro,
                    atualizacaoRegistro;
            int idPeople;
            Scanner prompt = new Scanner(System.in);
            //Recebendo dados
            System.out.println("Informe o nome ");
            name = prompt.nextLine();

            System.out.println("Informe o email");
            email = prompt.nextLine();

            String sql = "select * from People where nomePeople like '"+ name +"%' and emailPeople like '"+email+"%';";
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
            } catch (Exception exception) {
                return;
            }
            while (rs.next()){
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("Id:" + (idPeople = rs.getInt("idPeople")));
                System.out.println("Nome:" + (nomePeople = rs.getString("nomePeople").toString()));
                System.out.println("Email:" + (emailPeople = rs.getString("emailPeople").toString()));
                System.out.println("Sexo:" + (sexoPeople = rs.getString("sexoPeople").toString()));
                System.out.println("Nascimento:" + (nascimentoPeople = rs.getString("nascimentoPeople").toString()));
                System.out.println("Telefone:" + (telefonePeople = rs.getString("telefonePeople").toString()));
                System.out.println("Data de Criação do Registro:" + (criaçãoRegistro = rs.getDate("criacaoRegistro").toString()));
                System.out.println("Data da utima alteração do Registro:" + (atualizacaoRegistro = rs.getDate("atualizacaoRegistro").toString()));
                System.out.println("---------------------------------------------------------------------------------------------");
            }
        }
    }
