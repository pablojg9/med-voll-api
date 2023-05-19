package med.voll.api.medico.record;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.record.EnderecoRecord;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecord endereco) {}
