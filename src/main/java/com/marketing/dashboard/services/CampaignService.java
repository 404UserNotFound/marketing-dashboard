package com.marketing.dashboard.services;

import com.marketing.dashboard.entities.Campaign;
import com.marketing.dashboard.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;


    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return  campaignRepository.findById(id).orElse(null);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Campaign campaign, Long id) {
        Campaign existingCampaign = campaignRepository.findById(id).orElse(null);
        if (existingCampaign != null) {
            existingCampaign.setCampaignName(campaign.getCampaignName());
            existingCampaign.setCampaignDescription(campaign.getCampaignDescription());
            existingCampaign.setCampaignStatus(campaign.getCampaignStatus());
            existingCampaign.setCampaignBudget(campaign.getCampaignBudget());
            existingCampaign.setCampaignStartDate(campaign.getCampaignStartDate());
            existingCampaign.setCampaignEndDate(campaign.getCampaignEndDate());
            return campaignRepository.save(existingCampaign);
        } else {
            return null;
        }
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
