package com.marketing.dashboard.services;

import com.marketing.dashboard.entities.CampaignChannel;
import com.marketing.dashboard.repositories.CampaignChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignChannelService {

    @Autowired
    private CampaignChannelRepository campaignChannelRepository;


    public List<CampaignChannel> getAllCampaignChannels() {
        return campaignChannelRepository.findAll();
    }

    public CampaignChannel getCampaignChannelById(Long id) {
        return campaignChannelRepository.findById(id).orElse(null);
    }

    public CampaignChannel createCampaignChannel(CampaignChannel campaignChannel) {
        return campaignChannelRepository.save(campaignChannel);
    }

    public void deleteCampaignChannel(Long id) {
        campaignChannelRepository.deleteById(id);
    }
}
