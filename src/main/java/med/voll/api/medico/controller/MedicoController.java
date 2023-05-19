package med.voll.api.medico.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.record.CadastroMedico;
import med.voll.api.medico.record.DadosAtualizacaoMedico;
import med.voll.api.medico.record.DadosMedicos;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public ResponseEntity<Page<DadosMedicos>> listaMedico(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(medicoRepository.findAllByAtivoTrue(pageable).map(DadosMedicos::new));
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dadosMedicos) {
        Medico medico = medicoRepository.getReferenceById(dadosMedicos.id());
        medico.informationUpdate(dadosMedicos);
    }
    @DeleteMapping("{id}")
    @Transactional
    public void deleteMedicoId(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }



}
