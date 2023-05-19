package med.voll.api.medico.record;

import med.voll.api.medico.enums.Especialidade;
import med.voll.api.medico.model.Medico;

public record DadosMedicos(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade) {

    public DadosMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());
    }
}
