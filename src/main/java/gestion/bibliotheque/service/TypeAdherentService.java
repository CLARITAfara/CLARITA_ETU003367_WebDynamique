package gestion.bibliotheque.service;

import gestion.bibliotheque.model.TypeAdherent;
import gestion.bibliotheque.repository.TypeAdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAdherentService {

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    public List<TypeAdherent> getAll() {
        return typeAdherentRepository.findAll();
    }

    public Optional<TypeAdherent> getById(Long id) {
        return typeAdherentRepository.findById(id);
    }

    public TypeAdherent create(TypeAdherent typeAdherent) {
        return typeAdherentRepository.save(typeAdherent);
    }

    // public TypeAdherent update(Long id, TypeAdherent updated) {
    //     return typeAdherentRepository.findById(id)
    //             .map(existing -> {
    //                 existing.setNomType(updated.getNomType());
    //                 existing.setQuotaMaxPret(updated.getQuotaMaxPret());
    //                 existing.setDureeMaxPret(updated.getDureeMaxPret());
    //                 existing.setQuotaMaxProlongement(updated.getQuotaMaxProlongement());
    //                 existing.setDureeMaxProlongement(updated.getDureeMaxProlongement());
    //                 existing.setUpdatedAt(java.time.LocalDateTime.now());
    //                 return typeAdherentRepository.save(existing);
    //             })
    //             .orElse(null);
    // }

    public void delete(Long id) {
        typeAdherentRepository.deleteById(id);
    }

}
