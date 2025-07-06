package gestion.bibliotheque.service;

import gestion.bibliotheque.model.*;
import gestion.bibliotheque.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProlongementPretService {

    @Autowired
    private ProlongementPretRepository prolongementPretRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private StatutPretRepository statutPretRepository;

    @Autowired
    private QuotaRepository quotaRepository;

    public boolean demanderProlongement(Long pretId) {
        Pret pret = pretRepository.findById(pretId).orElse(null);
        if (pret == null) return false;

        Long adherentId = pret.getAdherent().getId();
        Quota quota = quotaRepository.findByAdherentId(adherentId);

        // Vérifier quota
        if (quota == null || quota.getCurrProlongement() >= 2) {
            return false;
        }

        // Vérifier si déjà prolongé
        if (Boolean.TRUE.equals(pret.getEstProlonge())) {
            return false;
        }

        // Créer le prolongement
        ProlongementPret prolongement = new ProlongementPret();
        prolongement.setPret(pret);
        prolongement.setDateProlongement(LocalDate.now());
        prolongement.setDateRetourPrevue(pret.getDateRetourPrevue().plusDays(7));

        StatutPret statutEnAttente = statutPretRepository.findById(1L).orElse(null); // <- corrigé ici
        prolongement.setStatut(statutEnAttente);

        prolongementPretRepository.save(prolongement);

        // Mise à jour du prêt
        pret.setEstProlonge(true);
        pret.setDateRetourPrevue(prolongement.getDateRetourPrevue());
        pretRepository.save(pret);

        // Mise à jour du quota
        quota.setCurrProlongement(quota.getCurrProlongement() + 1);
        quotaRepository.save(quota);

        return true;
    }
}
