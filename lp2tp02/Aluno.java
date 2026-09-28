/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedev.lp2tp02;
import java.util.UUID;

/**
 *
 * @author aluno
 */
public class Aluno {
    private String endereco;
    private int idade;
    private String nome;
    private UUID uuid;
    
    public Aluno() {
        this.uuid = UUID.randomUUID();
    }
    
    //Setters
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setIdade(int idade){
       this.idade = idade;
    }
    
    public void setNome (String nome) {
        this.nome = nome;
    }
    
    public void setUuid (UUID uuid){
        this.uuid = uuid;
    }
    //Getters
    public String getEndereco() {
        return this.endereco;
    }
    public int getIdade() {
        return this.idade;
    }
    public String getNome() {
        return this.nome;
    }
    public UUID getUuid() {
        return this.uuid;
    }
    
}