
public class Cliente {
    private String name;
    private Long cpf;
    
    public Cliente(String name, Long cpf) {
        this.name = name;
        this.cpf = cpf;
    }
    public Cliente(){

    }
    public String getNome(){
        return name;
    }
    public Long getCpf(){
        return cpf;
    }
    public void setCpf(Long cpf){
        this.cpf = cpf;
    }
    public void setName(String name){
        this.name=name;
    }
}
