package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Adherent;
import gestion.bibliotheque.model.Quota;

public interface QuotaService {
    Quota getQuotaByAdherent(Adherent adherent);
    void incrementPret(Adherent adherent);
    void decrementPret(Adherent adherent);
    void incrementProlongement(Adherent adherent);
    void resetQuota(Adherent adherent);
}