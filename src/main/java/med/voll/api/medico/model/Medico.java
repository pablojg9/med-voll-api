package med.voll.api.medico.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.endereco.model.Endereco;
import med.voll.api.medico.enums.Especialidade;
import med.voll.api.medico.record.CadastroMedico;
import med.voll.api.medico.record.DadosAtualizacaoMedico;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(CadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void informationUpdate(DadosAtualizacaoMedico dadosMedicos) {
        if (dadosMedicos.nome() != null){
            this.nome = dadosMedicos.nome();
        }

        if (dadosMedicos.telefone() != null) {
            this.telefone = dadosMedicos.telefone();
        }

        if (dadosMedicos.endereco() != null) {
            this.endereco.atualizarInformacao(dadosMedicos.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}