package gestion.bibliotheque.service.impl;

 
import gestion.bibliotheque.model.Adherent;
import gestion.bibliotheque.model.Quota;
import gestion.bibliotheque.repository.QuotaRepository;
import gestion.bibliotheque.service.QuotaService;
import org.springframework.stereotype.Service;

@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository quotaRepository;

    public QuotaServiceImpl(QuotaRepository quotaRepository) {
        this.quotaRepository = quotaRepository;
    }

    @Override
    public Quota getQuotaByAdherent(Adherent adherent) {
        return quotaRepository.findByAdherent(adherent)
                .orElseThrow(() -> new RuntimeException("Quota introuvable pour l'adhérent"));
    }

    @Override
    public void incrementPret(Adherent adherent) {
        Quota quota = getQuotaByAdherent(adherent);
        quota.setCurrPret(quota.getCurrPret() + 1);
        quotaRepository.save(quota);
    }

    @Override
    public void decrementPret(Adherent adherent) {
        Quota quota = getQuotaByAdherent(adherent);
        if (quota.getCurrPret() > 0) {
            quota.setCurrPret(quota.getCurrPret() - 1);
            quotaRepository.save(quota);
        }
    }

    @Override
    public void incrementProlongement(Adherent adherent) {
        Quota quota = getQuotaByAdherent(adherent);
        quota.setCurrProlongement(quota.getCurrProlongement() + 1);
        quotaRepository.save(quota);
    }

    @Override
    public void resetQuota(Adherent adherent) {
        Quota quota = getQuotaByAdherent(adherent);
        quota.setCurrPret(0);
        quota.setCurrProlongement(0);
        quotaRepository.save(quota);
    }
}
   
 